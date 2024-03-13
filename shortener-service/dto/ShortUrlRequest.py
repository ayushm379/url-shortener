from marshmallow import Schema, fields, ValidationError, validates

class ShortUrlRequest(Schema):
    url = fields.Str(required=True, error_messages="Url not provided")

shortUrlRequest = ShortUrlRequest()