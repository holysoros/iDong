def upload(self, request):
    try:
        image = request.FILES.get('image')
        if(self.checkImage(image).flag):
            filename = '%s%s' % ("fortest", image.name)
            #  get sae storage upload api, just for example
            cs = getapi()
            # upload the source image
            aeBody = cs.upload(destFileName=filename.encode('utf-8'), srcFile=image)
            #  create thumbs and upload
            if aeBody['errno'] == 0:
               image.seek(0)
               thumb = Image.open(image)
               if thumb.mode not in ('L', 'RGB'):
                    thumb = thumb.convert('RGB')
               thumb.thumbnail((128, 128), Image.ANTIALIAS)
               output = StringIO()
               thumb.save(output, thumb.format, quality = 100)
               output.seek(0)
               thumbname = 'thumb/%s' % (filename.encode('utf-8'), )
               aeBody = cs.uploadimg(destFileName=thumbname, srcFile=output)
               output.close()
               self.result = Result(True, 'OK', '', cs.getUrl(filename), 3)
           else
               self.result = Result(False, aeBody['errmsg'], '', '', 3)
    except Exception, ex:
        info = traceback.format_exc()
        print info
        self.result = Result(False, '"'+filename+'",'+info, '', '', 3)

