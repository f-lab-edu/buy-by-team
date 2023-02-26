#!/bin/bash

ABSPATH=$(readlink -f $0)

# ABSDIR : 현재 deploy.sh 파일 위치의 경로
ABSDIR=$(dirname $ABSPATH)

# import profile.sh, switch.sh
source ${ABSDIR}/profile.sh
source ${ABSDIR}/switch.sh

REPOSITORY=/home/ubuntu/deploy

cd $REPOSITORY

JAR_NAME=$(ls $REPOSITORY/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/$JAR_NAME

IDLE_PROFILE=$(find_idle_profile)
IDLE_PORT=$(find_idle_port)

echo "> $IDLE_PORT 에서 구동중인 애플리케이션 pid 확인"
IDLE_PID=$(lsof -ti tcp:${IDLE_PORT})

if [ -z $IDLE_PID ]
then
  echo "> 구동중인 애플리케이션이 없습니다."
else
  echo "> kill -9 IDLE_PID"
  kill -15 $IDLE_PID
  sleep 5
fi

echo "> $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행"
nohup java -javaagent:/home/ubuntu/scouter/agent.java/scouter.agent.jar \
  -Dscouter.config=/home/ubuntu/scouter/agent.java/conf/scouter.conf \
  -jar $JAR_PATH --spring.profiles.active=$IDLE_PROFILE --logging.file.path=/home/ubuntu/log/ \
  --logging.level.org.hibernate.SQL=DEBUG >> /home/ubuntu/log/deploy.log 2>/home/ubuntu/log/error.log &


echo "> $IDLE_PROFILE 10초 후 Health check 시작"
echo "> curl -s http://127.0.0.1:$IDLE_PORT/health "
sleep 10

for retry_count in {1..10}
do
  response=$(curl -s http://127.0.0.1:$IDLE_PORT/health)
  up_count=$(echo $response | grep 'healthy' | wc -l)

  if [ $up_count -ge 1 ]
  then # $up_count >= 1 ("healthy" 문자열이 있는지 검증)
      echo "> Health check 성공"
      echo "> 현재의 idle port로 트래픽 전환"
      switch_proxy
      break
  else
      echo "> Health check의 응답을 알 수 없거나 혹은 status가 healthy가 아닙니다."
      echo "> Health check: ${response}"
  fi

  if [ $retry_count -eq 10 ]
  then
    echo "> Health check 실패. "
    echo "> Nginx에 연결하지 않고 배포를 종료합니다."
    exit 1
  fi

  echo "> Health check 연결 실패. 재시도..."
  sleep 10
done
