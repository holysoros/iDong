
from django.conf.urls import patterns, include, url

urlpatterns = patterns('idong.views',
    url(r'^users/$',    'user_list'),
    url(r'^parties/$',  'party_list'),
    url(r'^parties/(?P<pk>[0-9]+)/$', 'party_detail'),
)
