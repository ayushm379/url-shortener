import os
from config.external import get_range
from db.Url import Url
from config.cache import redis_client


COUNTER_KEY = "index_counter"
START_KEY = "start_key"
END_KEY = "end_key"
MAX_URL_LENGTH = int(os.environ.get('MAX_URL_LENGTH'))
MAX_VALUE = 62 ** MAX_URL_LENGTH


def get_next_number():
    return redis_client.incr(COUNTER_KEY)


def set_range(token):
    range = get_range(token)
    redis_client.set(START_KEY, range["start"])
    redis_client.set(END_KEY, range["end"])


def is_in_range(counter):
    range_start = redis_client.get(START_KEY) or None
    range_end = redis_client.get(END_KEY) or None
    if range_start is None or range_end is None:
        return False
    return range_start <= counter and range_end >= counter


def convert_to_base62(number):
    if number > MAX_VALUE:
        return None
    
    base62_chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    result = ''
    
    while num > 0:
        remainder = num % 62
        result = base62_chars[remainder] + result
        num //= 62
    
    padded_result = result.zfill(MAX_URL_LENGTH)
    return padded_result[-MAX_URL_LENGTH:] 