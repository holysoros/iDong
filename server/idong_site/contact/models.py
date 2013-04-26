from django.db import models

# Create your models here.
class Contact(models.Model):
    name = models.CharField(max_length=50)
    phone = models.CharField(max_length=20)
    title = models.CharField(max_length=50)
    address = models.CharField(max_length=100)

    def __unicode__(self):
        return self.name + ' ' + self.title
