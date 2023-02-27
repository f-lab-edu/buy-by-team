#!/bin/bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo "> 전환할 Port: $IDLE_PORT"
    echo "> Port 전환"

    # | sudo tee ~ : 앞에서 넘긴 문장을 service-url.inc에 덮어씀
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc

    echo "> nignx Reload"
    sudo service nginx reload
}

function switch_blue_to_green() {
    idle_port_values=$(find_idle_port)
    current_port_values=$(find_current_port)

    IFS=',' read -ra IDLE_PORTS <<< "$idle_port_values"
    IFS=',' read -ra CURRENT_PORTS <<< "$current_port_values"

    echo "> UP으로 전환할 Port: ${IDLE_PORTS[0]}, ${IDLE_PORTS[1]}"
    echo "> green 서버 Up"

    curl -X PUT http://127.0.0.1:${IDLE_PORTS[0]}/health/up
    curl -X PUT http://127.0.0.1:${IDLE_PORTS[1]}/health/up

    echo "> DOWN으로 전환할 Port: ${CURRENT_PORTS[0]}, ${CURRENT_PORTS[1]}"
    echo "> blue 서버 Down"

    curl -X PUT http://127.0.0.1:${CURRENT_PORTS[0]}/health/down
    curl -X PUT http://127.0.0.1:${CURRENT_PORTS[1]}/health/down
}