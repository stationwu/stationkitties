#!/bin/sh

rm -f ~/app0.log;

git pull;

mvn clean package -DskipTests=true;

for f in target/*.jar; do
    java -jar "$f" | tee ~/app0.log
    break
done
