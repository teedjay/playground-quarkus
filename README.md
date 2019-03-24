# playground-quarkus
Playing around with MicroProfile using Quarkus.io

## TODO
These are the things I want to test with Quarkus.
- [x] Run with Java 11, test with JUnit5 + RestAssured 
- [x] JAX-RS : Simple API and JSON-B
- [x] JAX-RS : ExceptionMapper
- [x] CDI : Scoped Injection
- [x] MicroProfile configuration
- [x] MicroProfile OpenAPI (annotating with own documentation)
- [x] MicroProfile Health (with custom health check)
- [x] MicroProfile Metrics (with custom metrics)
- [ ] MicroProfile TypeSafe REST Client
- [ ] MicroProfile FaultTolerance
- [x] Add SwaggerUI start page at http://localhost:8080/
- [x] MongoDB (needs a MongoDB running on default port)
- [ ] Dockerfile for executable jar (alpine linux openjdk11)
- [ ] Native executable using GraalVM

## Some comments ...
> Doesn't require JAX-RS application (@ApplicationPath) <br/>
> Web pages in META-INF/resources (no webapp) <br/>
> Need to define SureFire plugin explicit to run tests from Maven <br/>
> Debugger works nice from IntelliJ (`Run -> Attach to Process`) <br/>
> Native version with `graalvm-ce-1.0.0-rc14` and Quarkus 0.12.0 started in 0.006s on my iMac<br/>

## Pre-requisite ...
I'm using Open JDK 11 with Maven 3.5 and a MongoDB on default port.
```
$ java -version
openjdk version "11.0.2" 2019-01-15
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.2+9)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.2+9, mixed mode)

$ mvn -version
Apache Maven 3.5.4 (1edded0938998edf8bf061f1ceb3cfdeccf443fe; 2018-06-17T20:33:14+02:00)
Maven home: /usr/local/Cellar/maven/3.5.4/libexec

$ docker run --name mongodb -p 27017:27017 -d mongo:latest
```

## URL's to check out
- SwaggerUI : http://localhost:8080/
- OpenAPI : http://localhost:8080/openapi 
- Metrics : http://localhost:8080/metrics
- Metrics : http://localhost:8080/metrics/application
- Health : http://localhost:8080/health

```
curl http://localhost:8080/hello
curl -X POST -H "Content-Type: text/plain" -d "teedjay" http://localhost:8080/hello
curl http://localhost:8080/mongo
```

## Commands to use
```
# list all Quarkus extensions that can be used in the pom.xml
mvn quarkus:list-extensions

# clean and remove old artificts
mvn clean

# compile and run in dev mode (hot reload and debugging by default on port 5005)
mvn compile quarkus:dev

# build from scratch and run all tests
mvn clean test

# create runnable artifacts (SwaggerUI on http://localhost:8080/)
mvn clean package
java -jar target/quarkus-1.0.0-SNAPSHOT-runner.jar
```
