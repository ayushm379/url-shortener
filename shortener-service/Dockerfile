FROM python:3.8-slim-buster

WORKDIR /app

RUN apt-get update && \
    apt-get install -y git

COPY . .
RUN pip3 install -r requirements.txt


CMD [ "python3", "-m", "flask", "run" ]
EXPOSE 5000