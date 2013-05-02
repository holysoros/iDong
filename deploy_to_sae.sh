#!/bin/bash
set -x

# src_dir is the root directory of all things you want
# to deploy to SAE svn repository.
# Usually, it should be changed.
src_dir=$1

svn_dir=/var/tmp/svn_dir

# remove the deleted items in git from svn repository
svn_rm_deleted() {
    deleted_items=`svn status | grep ^! | awk '{ print $2}'`
    if ! [ -z $deleted_items ]; then
        svn rm $deleted_items
    fi
}

# add items in git from svn repository
svn_add_new() {
    new_items=`svn status | grep ^? | awk '{ print $2}'`
    if ! [ -z $new_items ]; then
        svn add $new_items
    fi
}

echo "Checkout svn repository from SAE"
svn co https://svn.sinaapp.com/holyweibo/ $svn_dir --username holysoros@163.com --password $SVN_PASSWD --no-auth-cache || exit 1

echo "Sync from git repository"

cd $src_dir
git diff --relative --no-color HEAD^..HEAD >/var/tmp/diff.patch

cd $svn_dir/1
patch -p1 < /var/tmp/diff.patch

cd $svn_dir/1
svn_rm_deleted
svn_add_new

echo "Deploy to SAE"
svn ci -m "$TRAVIS_COMMIT" --username holysoros@163.com --password $SVN_PASSWD --no-auth-cache || exit 1

echo "Done"
