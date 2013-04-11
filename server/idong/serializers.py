from rest_framework import serializers
from idong.models import User, Party, UserParty

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model  = User;
        fields = ('id', 'name')


class PartySerializer(serializers.ModelSerializer):
    class Meta:
        model  = Party
        fields = ('laucher_user', 'title', 'category', 'start_time', 'end_time', 'description', 'location', 'image_url')

class UserPartySerializer(serializers.ModelSerializer):
    class Meta:
        model  = UserParty
        fields = ('user', 'party', 'flag')
