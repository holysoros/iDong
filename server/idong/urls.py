from django.conf.urls import patterns, include, url
from rest_framework.urlpatterns import format_suffix_patterns
from idong import views

urlpatterns = patterns('idong.views',
    url(r'^users/$',    views.UserList.as_view()),
    url(r'^parties/$',  views.PartyList.as_view()),
    url(r'^parties/party=(?P<pk>[0-9]+)/$', views.PartyDetail.as_view()),
    url(r'^userparty/$', views.UserParty.as_view()),
    url(r'^userparty/user=(?P<user>[0-9]+)/$', views.UserParty.as_view()),
    url(r'^userparty/party=(?P<user>[0-9]+)/$', views.UserParty.as_view()),
)
urlpatterns = format_suffix_patterns(urlpatterns)
