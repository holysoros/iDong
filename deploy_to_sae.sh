#!/bin/sh
set -x
TRAVIS_COMMIT='commit nothing'
SVN_PASSWD=jbcb

# src_dir is the root directory of all things you want
# to deploy to SAE svn repository.
# Usually, it should be changed.
src_dir=server/idong_site

svn_dir=/var/tmp/svn_dir

# remove the deleted items in git from svn repository
svn_rm_deleted() {
    deleted_items=`svn status | grep ^! | awk '{ print $2}'`
    if ! [ -z $deleted_items ]; then
        svn rm $deleted_items
    fi
}

mkdir -p $svn_dir
echo "Checkout svn repository from SAE"
svn co https://svn.sinaapp.com/holyweibo/ $svn_dir --username holysoros@163.com --password $SVN_PASSWD --no-auth-cache || exit 1

echo "Sync from git repository"

#cd $src_dir
#git diff --relative --no-color HEAD^..HEAD >/tmp/diff.patch
#patch -p1 < /tmp/diff.patch

# `1` is the revision of sae application
rm -rf $svn_dir/1/*
cp -rf $src_dir/* $svn_dir/1/ || exit 1

cd $svn_dir/1
svn_rm_deleted

# Reporting some files are already under version control
# which can be safely ignored
#svn add * 2>/dev/null

echo "Deploy to SAE"
svn ci -m "$TRAVIS_COMMIT" --username holysoros@163.com --password $SVN_PASSWD --no-auth-cache || exit 1

echo "Done"
