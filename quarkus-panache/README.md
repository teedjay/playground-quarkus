# quarkus-panache
Playing around with Panache and PostgreSQL using Quarkus.

## Pre-requisite ...
Needs a running PostgreSQL server to work.
```
docker run --name postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -e POSTGRES_DB=teedjay -p 5432:5432 -d postgres:latest
```

## To run in dev mode
```
mvn clean compile quarkus:dev
```
