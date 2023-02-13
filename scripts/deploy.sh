#!/bin/bash

REPOSITORY=/home/ubuntu/deploy

cd $REPOSITORY

JAR_NAME=$(ls $REPOSITORY/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/$JAR_NAME

CURRENT_PID=$(pgrep -f *.jar)

if [ -z $CURRENT_PID ]
then
  echo "> Nothing to end."
else
  echo "> kill -9 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> $JAR_PATH deploy"
nohup java -jar $JAR_PATH --spring.profiles.active=prod --logging.file.path=/home/ubuntu/log/ \
--logging.level.org.hibernate.SQL=DEBUG >> /home/ubuntu/log/deploy.log 2>/home/ubuntu/log/error.log &