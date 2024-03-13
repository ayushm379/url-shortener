import redis
import os

CACHE_HOST = os.environ.get('CACHE_HOST')

redis_client = redis.Redis(host=CACHE_HOST, port=6379, db=0)
