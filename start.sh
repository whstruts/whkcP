#!/bin/bash

# 设置JVM堆内存大小
JAVA_OPTS="-Xms256m -Xmx512m"

# 启动应用
java $JAVA_OPTS -jar target/whkc-hbqj.jar
