#!/bin/bash

# 현재 profile 찾기
function find_current_profile()
{
		RESPONSE_CODE=$(curl -o /dev/null -w "%{http_code}" http://127.0.0.1/profile)

    if [ ${RESPONSE_CODE} -ge 400 ] # 400 보다 크면 (40x/50x 에러 모두 포함)
    then
        CURRENT_PROFILE=prod2
    else
        CURRENT_PROFILE=$(curl -s http://127.0.0.1/profile)
    fi

		echo "${CURRENT_PROFILE}"
}

# 현재 port 찾기
function find_current_port()
{
    CURRENT_PROFILE=$(find_current_profile)

    if [ ${CURRENT_PROFILE} == prod1 ]
    then
      echo "8080"
    else
      echo "8081"
    fi
}

# 현재 사용하지 않는 idle profile 찾기
function find_idle_profile()
{
    CURRENT_PROFILE=$(find_current_profile)

    if [ ${CURRENT_PROFILE} == prod1 ]
    then
      IDLE_PROFILE=prod2
    else
      IDLE_PROFILE=prod1
    fi

    echo "${IDLE_PROFILE}"
}

# idle profile의 port 찾기
function find_idle_port()
{
    IDLE_PROFILE=$(find_idle_profile)

    if [ ${IDLE_PROFILE} == prod1 ]
    then
      echo "8080"
    else
      echo "8081"
    fi
}