from flask import request, jsonify
from flask_restful import Api, Resource
import os
import logging

from app import app
from config.range import get_next_number, convert_to_base62, is_in_range
from config.cache import redis_client
from dto.ShortUrlRequest import shortUrlRequest
from db.Urls import Url

logging.basicConfig(level=logging.INFO)
formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(message)s')

api = Api(app)

class HealthCheck(Resource):
    def get(self):
        return {'status': 'UP'}

api.add_resource(HealthCheck, '/actuator/health')


PORT = os.environ.get('PORT')

@app.post('/short-url')
def get_short_url():
    data = request.json
    print("Received Request Body", data)
    shortUrlRequest.loads(data)
    print("Validated Request Body")
    
    number = get_next_number()
    
    if not is_in_range(number):
        set_range(request.headers.get('Authorization'))
        number = get_next_number()

    print("Current Number", number)
    hash = convert_to_base62(number)
    print("Hash Generated", hash)

    url = Url(index=number, short_url=hash, long_url=data["url"])
    url.save()

    return jsonify({
        "success": true,
        "short_url": hash
    })


@app.get("redirect/<hash>")
def get_long_url(hash):
    long_url = redis_client.get(hash)
    if long_url is None:
        document = Url.objects(short_url=hash).first()
        if document is None:
            return jsonify({
                "success": false,
                "error": "Hash not present"
            })
        long_url = document["long_url"]

    redis_client.put(hash, long_url)
    return jsonify({
        "success": true,
        "long_url": long_url
    })
 
