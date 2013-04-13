from rest_framework import generics 
from idong.models import User, Party, UserParty
from idong.serializers import UserSerializer, PartySerializer, UserPartySerializer


class UserList(generics.ListCreateAPIView):
    model = User
    serializer_class = UserSerializer

class PartyList(generics.ListCreateAPIView):
    model = Party
    serializer_class = PartySerializer

class PartyDetail(generics.RetrieveUpdateDestroyAPIView):
    model = Party
    serializer_class = PartySerializer


class UserParty(generics.ListCreateAPIView):
    model = UserParty
    serializer_class = UserPartySerializer
