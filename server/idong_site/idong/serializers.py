from rest_framework import serializers
from idong.models import Party
from django.contrib.auth.models import User

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('id', 'username', )

class PartyBriefSerializer(serializers.ModelSerializer):
    #participant_num = serializers.Field(source='participants.all().count()')
    participant_num = serializers.Field(source='get_participants_num')
    liker_num = serializers.Field(source='get_likers_num')
    #thumbnail = serializers.Field(source='image_urls[0]')
    thumbnail = serializers.Field(source='get_thumbnail')
    class Meta:
        model = Party
        fields = ('id', 'title', 'category', 'start_time', 'end_time', 'location',
            'participant_num', 'liker_num', 'thumbnail')

class PartyDetailSerializer(serializers.ModelSerializer):
    participant_num = serializers.Field(source='get_participants_num')
    liker_num = serializers.Field(source='get_likers_num')
    laucher_user = serializers.Field(source='laucher_user.username')
    class Meta:
        model = Party
        # TODO: Include participants name list
        # to show who also participate
        fields = ('id', 'title', 'category', 'start_time', 'end_time', 'location',
            'participant_num', 'liker_num', 'image_urls', 'laucher_user', 'description', )
