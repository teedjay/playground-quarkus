# quarkus-mongo
Playing around with MongoDB using Quarkus.

## Pre-requisite ...
Needs a running MongoDB server to work.
```
docker run --name mongodb -p 27017:27017 -d mongo:latest
```

## To run in dev mode
```
mvn clean compile quarkus:dev
```
