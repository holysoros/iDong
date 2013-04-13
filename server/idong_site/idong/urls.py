from django.conf.urls import patterns, include, url
from rest_framework.urlpatterns import format_suffix_patterns
from idong import views

urlpatterns = patterns('snippets.views',
    url(r'^users/$', views.UserList.as_view()),
    url(r'^users/(?P<pk>[0-9]+)/$', views.UserDetail.as_view()),
    url(r'^parties/$', views.PartyList.as_view()),
    url(r'^parties/(?P<pk>\d+)/$', views.PartyDetail.as_view()),
    url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework'))
)

urlpatterns = format_suffix_patterns(urlpatterns)
