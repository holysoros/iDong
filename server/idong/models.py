from django.db import models



class User(models.Model):
    name = models.CharField(max_length='100', blank=True)

class Party(models.Model):    
    laucher_user= models.ForeignKey(User, related_name='user')
    title       = models.CharField(max_length='50', blank=True)
    category    = models.PositiveSmallIntegerField(blank=True)
    start_time  = models.DateTimeField()
    end_time    = models.DateTimeField()

    description = models.CharField(max_length='500', blank=True)
    location    = models.CharField(max_length='100', blank=True)
    image_url   = models.CharField(max_length='4000', blank=True)

    user_party  = models.ManyToManyField(User, related_name='user_party', through='UserParty')
    
    class Meta:
        ordering = [ '-start_time' ]


class UserParty(models.Model):
    user    = models.ForeignKey(User)
    party   = models.ForeignKey(Party)
    flag    = models.PositiveSmallIntegerField(blank=True)
