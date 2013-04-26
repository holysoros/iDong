from django.conf.urls import patterns, url

from contact import views

urlpatterns = patterns('',
    url(r'^$', views.index, name='index'),
    url(r'^web$', views.webindex, name='webindex'),
    url(r'^login', views.login, name='login'),
)
