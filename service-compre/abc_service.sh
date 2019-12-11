#!/bin/bash

## 无需修改
if [ "$BASE_DIR" == "" ];then
  BASE_DIR=$(dirname $0)
fi

if [ "$PID_NAME" == "" ];then
  PID_NAME=abcservice.pid
fi

## 执行的jar包，各业务需各自调整
RUN_JAR="target/service-compre-0.0.1-SNAPSHOT.jar"

## 判断服务是不是在容器里，DOCKER_ENV=1 为容器中
if [[ ! -n "$DOCKER_ENV" ]] || [[ "$DOCKER_ENV" -ne 1 ]] ;then
  start_log=/tmp/service_start.log
  ## 获取内网IP
  MACHINE_IP=`ifconfig eth0 | awk '{if ( $1 == "inet") print $2}'`
  ## 服务非容器中启动端口
  SERVICE_PORT='8591'
  ## java服务运行参数（非容器环境）
  RUN_JAR_PARAM="--server.port=${SERVICE_PORT} --eureka.instance.instanceId=${MACHINE_IP}:${SERVICE_PORT} --eureka.instance.hostname=${MACHINE_IP} --eureka.instance.preferIpAddress=false"
  PID_FILE="/tmp/$PID_NAME"
elif [ "$DOCKER_ENV" -eq 1 ];then
  start_log=$BASE_DIR/logs/service_start.log
  ## java服务运行参数（容器环境）
  RUN_JAR_PARAM="--server.port=${DOCKER_SERVICE_PORT} --eureka.instance.instanceId=${DOCKER_INSTANCE_HOST}:${DOCKER_SERVICE_PORT} --eureka.instance.hostname=${DOCKER_INSTANCE_HOST} --eureka.instance.preferIpAddress=false "
  PID_FILE='/var/run/$PID_NAME'
else
  echo $"Usage: abc_service.sh {start|stop|restart|status}"
  exit 110
fi


start() {
  if [ "$DOCKER_ENV" == 1 ];then
    java -jar ${BASE_DIR}/${RUN_JAR} ${RUN_JAR_PARAM} > $start_log 2>&1
  else
    cd $BASE_DIR
    java -jar ${BASE_DIR}/${RUN_JAR} ${RUN_JAR_PARAM} > $start_log 2>&1 &
    pid=`echo "$!"`
    echo $pid > $PID_FILE
    echo "service is started, pid is $pid"
  fi
}

stop (){
  pid=`cat $PID_FILE`
  kill $pid
  sleep 5
  if [ -n `ps aux |grep $pid | grep -v grep` ];then
    echo "service is stop"
  else
    sleep 5
    if [ -n `ps aux |grep $pid | grep -v grep` ];then
      echo "service is stop"
    else
      echo "some error,try stop again"
      exit 110
    fi
  fi
}

rh_status() {
  ps aux |grep `cat $PID_FILE` | grep -v grep > /dev/null
  if [ $? -eq 0 ];then
    echo "service is running"
  else
    echo "serivce is not start"
  fi
}

case "$1" in
  start)
    start
    ;;
  stop)
    stop
    ;;
  status)
    rh_status
    ;;
  restart)
    stop
    start
    ;;
  *)
  echo $"Usage: abc_service.sh {start|stop|restart|status}"
  exit 110
esac