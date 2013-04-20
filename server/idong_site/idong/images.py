from django.views.decorators.csrf import csrf_exempt  
from PIL import Image
from django.core.files.base import ContentFile
import StringIO
from django.http import HttpResponse
from django.utils import simplejson

def save_to_storage(name, content):
    from os import environ
    online = environ.get("APP_NAME", "")

    if online:
        import sae.const
        access_key = sae.const.ACCESS_KEY
        secret_key = sae.const.SECRET_KEY
        appname = sae.const.APP_NAME
        domain_name = "images"

        import sae.storage
        s = sae.storage.Client()
        ob = sae.storage.Object(content.read())
        url = s.put(domain_name, name, ob)
        return url
    else:
        return "offline"

def create_thumb(srcImage, size):
    srcImage.seek(0)
    thumb = Image.open(srcImage)
    if thumb.mode not in ('L', 'RGB'):
        thumb = thumb.convert('RGB')
    thumb.thumbnail((128,128),Image.ANTIALIAS)
    return thumb
 
@csrf_exempt
def upload(request):
    content = request.FILES['image']
    url = save_to_storage(content.name, content)
    thumb = create_thumb(content, 128)
    output = StringIO.StringIO()
    thumb.save(output, thumb.format, quality = 100)
    output.seek(0)
    thumbname = 'thumbs/%s' % (content.name.encode('utf-8'), )
    turl = save_to_storage(thumbname, output)
    output.close()
    result_json={'imgurl':url,'thumburl':turl}
    return HttpResponse(simplejson.dumps(result_json,ensure_ascii = False))

