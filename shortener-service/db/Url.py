from config.mongo import db

class Url(db.Document):
    index = db.IntField()
    short_url = db.StringField(primary_key=True)
    long_url = db.StringField()

