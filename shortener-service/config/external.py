import requests
import os

RANGE_SERVICE_HOST = os.environ.get('RANGE_SERVICE_HOST')

def get_range(token):
    api_url = f"{RANGE_SERVICE_HOST}/range"
    headers = {'Authorization': f"Bearer {token}"}  

    try:
        response = requests.get(api_url, headers=headers)
        if response.status_code == 200:
            data = response.json()
            if data.success:
                return data.range
            else:
                return None
        else:
            return jsonify({'error': 'Failed to fetch data from the API'}), response.status_code
    except Exception as e:
        return jsonify({'error': str(e)}), 500
        