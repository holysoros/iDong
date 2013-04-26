from django.http import HttpResponse
from contact.models import Contact
from django.core import serializers
from django.contrib.auth.decorators import login_required

@login_required(login_url='/accounts/login/')
def index(request):
    all_contacts = Contact.objects.all()
    data = serializers.serialize("json", Contact.objects.all())
    return HttpResponse(data)

def people(request, name):
    contact = Contact.objects.filter(name__startswith=name)
    if contact:
        response = serializers.serialize("json", contact)
    else:
        response = 'No such person'
    return HttpResponse(response)

def webindex(request):
    from django.template import Context, loader
    from django.contrib.auth import authenticate, login
    from django.shortcuts import redirect

    if request.user.is_authenticated():
        all_contacts = Contact.objects.all()
        template = loader.get_template('contact/index.html')
        context = Context({'all_contacts': all_contacts})
        return HttpResponse(template.render(context))
    else:
        template = loader.get_template('contact/login.html')
        return redirect('/contact/login/?next=%s' % request.path)

def login(request):
    if request.method == 'GET':
        from django.template import Context, loader
        from django.template import RequestContext
        template = loader.get_template('contact/login.html')
        return HttpResponse(template.render(RequestContext(request)))
    else:
        from django.contrib.auth import authenticate, login
        username = request.POST['username']
        password = request.POST['password']
        user = authenticate(username=username, password=password)
        if user is not None:
            if user.is_active:
                login(request, user)
                str = "Hi %s, you successfully login" % username
                return HttpResponse(str)
            else:
                return HttpResponse("Hi, your account have not been activated")
        else:
            return HttpResponse("Hi, you have not been registered")
