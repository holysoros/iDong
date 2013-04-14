from PIL import image

def upload(request):
    reqfile = request.FILES['photo']
    image   = image.open(reqfile)

    image.thumbnail((128,128), Image.ANTIALIAS)
    image.save("/home/bing/1.jpeg", "jpeg")
    return HttpResponse("success.")
