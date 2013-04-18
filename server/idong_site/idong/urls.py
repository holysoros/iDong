from django.conf.urls import patterns, include, url
from rest_framework.urlpatterns import format_suffix_patterns
from idong import views, images

urlpatterns = patterns('snippets.views',
    url(r'^users/$', views.UserList.as_view()),
    url(r'^users/(?P<pk>[0-9]+)/$', views.UserDetail.as_view()),
    url(r'^parties/$', views.PartyList.as_view()),
    url(r'^parties/(?P<pk>\d+)/$', views.PartyDetail.as_view()),
    url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework')),
    url(r'^upload$', images.upload),
    url(r'^adduserimg/$', views.UserImages.as_view()),
    url(r'^userimg/user=(?P<user>[0-9]+)/$', views.UserImages.as_view()),
    url(r'^partyimg/party=(?P<party>[0-9]+)/$', views.PartyImages.as_view()),
    url(r'^addpartyimg/$', views.PartyImages.as_view())
)

urlpatterns = format_suffix_patterns(urlpatterns)
