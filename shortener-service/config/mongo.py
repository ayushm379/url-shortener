from flask_mongoengine import MongoEngine
import os
from app import app

DATABASE_HOST = os.environ.get('DATABASE_HOST')
DATABASE_NAME = os.environ.get('DATABASE_NAME')
DATABASE_USERNAME = os.environ.get('DATABASE_USERNAME')
DATABASE_PASSWORD = os.environ.get('DATABASE_PASSWORD')


app.config['MONGODB_SETTINGS'] = {
    'db': DATABASE_NAME,
    'host': DATABASE_HOST,
    'port': 27017,
    'username': DATABASE_USERNAME,
    'password': DATABASE_PASSWORD
}
db = MongoEngine()
db.init_app(app)
