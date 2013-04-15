from django.shortcuts import render
from django.shortcuts import render_to_response  
from django.views.decorators.csrf import csrf_exempt  
   
def select(request):  
    return render_to_response('upload.html', {"value":"upload"})


 
@csrf_exempt  
def upload(request):  
    content = request.FILES['image']  
      
    from os import environ  
    online = environ.get("APP_NAME", "")   
  
    if online:  
        import sae.const  
        access_key = sae.const.ACCESS_KEY  
        secret_key = sae.const.SECRET_KEY  
        appname = sae.const.APP_NAME  
        domain_name = "xgoimages" 
          
        import sae.storage  
        s = sae.storage.Client()  
        ob = sae.storage.Object(content.read())  
        url = s.put(domain_name, content.name, ob)  
        return render_to_response('upload.html', {"value":url})  
    else:  
        return render_to_response('upload.html', {"value":"upload failed"})
