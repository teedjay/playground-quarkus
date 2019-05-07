# quarkus-kafka
Playing around with Kafka using Quarkus.

## Pre-requisite ...
Needs a running Kafka server to work.
```
docker run -d --name kafka -p 9092:9092 -e KAFKA_CREATE_TOPICS="teedjay-inbound:1:1,teedjay-outbound:1:1" blacktop/kafka
```

## To run in dev mode
```
mvn clean compile quarkus:dev
```
