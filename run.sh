#!/bin/bash

./gradlew build

# Check if the application is already running
PID=$(ps -ef | grep PullUp-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{ print $2 }')

if [ -z "$PID" ]
then
  echo "Application is not running."
else
  echo "Application is running, killing process $PID"
  kill -9 $PID
fi

echo "Starting Application"
nohup java -jar ./build/libs/PullUp-0.0.1-SNAPSHOT.jar &
