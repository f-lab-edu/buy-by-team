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
port_values=$(find_idle_port)

IFS=',' read -ra IDLE_PORTS <<< "$port_values"

echo "> ${IDLE_PORTS[0]} 에서 구동중인 애플리케이션 pid 확인"
IDLE_PID1=$(lsof -ti tcp:${IDLE_PORTS[0]})

if [ -z $IDLE_PID1 ]
then
  echo "> 구동중인 애플리케이션이 없습니다."
else
  echo "> kill -15 IDLE_PID1"
  kill -15 $IDLE_PID1
  sleep 5
fi

echo "> ${IDLE_PORTS[1]} 에서 구동중인 애플리케이션 pid 확인"
IDLE_PID2=$(lsof -ti tcp:${IDLE_PORTS[1]})

if [ -z $IDLE_PID2 ]
then
  echo "> 구동중인 애플리케이션이 없습니다."
else
  echo "> kill -15 IDLE_PID2"
  kill -15 $IDLE_PID2
  sleep 5
fi


echo "> $JAR_NAME 를 profile=$IDLE_PROFILE port=${IDLE_PORTS[0]} 로 실행"
nohup java -javaagent:/home/ubuntu/scouter/agent.java/scouter.agent.jar \
  -Dscouter.config=/home/ubuntu/scouter/agent.java/conf/was01.conf \
  -jar -Dspring.profiles.active=$IDLE_PROFILE $JAR_PATH --server.port=${IDLE_PORTS[0]} --logging.file.path=/home/ubuntu/log/ \
  --logging.level.org.hibernate.SQL=DEBUG >> /home/ubuntu/log/deploy.log 2>/home/ubuntu/log/error.log &

echo "> $JAR_NAME 를 profile=$IDLE_PROFILE port=${IDLE_PORTS[1]} 로 실행"
nohup java -javaagent:/home/ubuntu/scouter/agent.java/scouter.agent.jar \
  -Dscouter.config=/home/ubuntu/scouter/agent.java/conf/was02.conf \
  -jar -Dspring.profiles.active=$IDLE_PROFILE $JAR_PATH --server.port=${IDLE_PORTS[1]} --logging.file.path=/home/ubuntu/log/ \
  --logging.level.org.hibernate.SQL=DEBUG >> /home/ubuntu/log/deploy.log 2>/home/ubuntu/log/error.log &


echo "> $IDLE_PROFILE 10초 후 Health check 시작"
sleep 10

for retry_count in {1..10}
do
  response1=$(curl -s http://127.0.0.1:${IDLE_PORTS[0]}/health)
  up_count1=$(echo $response1 | grep 'UP' | wc -l)

  response2=$(curl -s http://127.0.0.1:${IDLE_PORTS[1]}/health)
  up_count2=$(echo $response2 | grep 'UP' | wc -l)

  if [ $up_count1 -ge 1 ] && [ $up_count2 -ge 1 ]
  then # $up_count >= 1 ("UP" 문자열이 있는지 검증)
      echo "> Health check 성공"
      echo "> 현재의 idle port로 트래픽 전환"
      switch_blue_to_green
      break
  else
      echo "> Health check의 응답을 알 수 없거나 혹은 status가 UP이 아닙니다."
      echo "> Health check for port=${IDLE_PORTS[0]}: ${response1}"
      echo "> Health check for port=${IDLE_PORTS[1]}: ${response2}"
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