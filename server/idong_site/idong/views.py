from idong.models import Party, UserImages, PartyImages
from idong.serializers import UserSerializer, PartyBriefSerializer, PartyDetailSerializer, UserImagesSerializer, PartyImagesSerializer
from rest_framework import generics
from django.contrib.auth.models import User
from rest_framework import permissions
from django.http import HttpResponse

class UserList(generics.ListAPIView):
    model = User
    serializer_class = UserSerializer

class UserDetail(generics.RetrieveAPIView):
    model = User
    serializer_class = UserSerializer


class PartyList(generics.ListAPIView):
    model = Party
    serializer_class = PartyBriefSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)

class PartyDetail(generics.RetrieveUpdateDestroyAPIView):
    model = Party
    serializer_class = PartyDetailSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)

class UserImages(generics.ListAPIView):
    model = UserImages
    serializer_class = UserImagesSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)

class PartyImages(generics.ListAPIView):
    model = PartyImages
    serializer_class = PartyImagesSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)
