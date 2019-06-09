# quarkus-undertow
Playing around with Undertow Predicate Language using Quarkus.

Check the undertow-handlers.conf for configuration.
More details here https://quarkus.io/guides/undertow-reference

## Pre-requisite ...
Needs a local running MongoDB server for tests to work (since it reverse proxies to mongo port).
```
docker run --name mongodb -p 27017:27017 -d mongo:latest
```

## To run in dev mode
```
mvn clean compile quarkus:dev
```
