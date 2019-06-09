# playground-quarkus
Playing around with MicroProfile using Quarkus.io.

Most tests are in the basic testing project, but scenarios that need external services 
such as database and queues are located in separate sub-projects with instructions on 
how to start pre-requsites using Docker.

- [Basic Quarkus Testing](quarkus-basics/README.md)
- [Kafka with Quarkus](quarkus-kafka/README.md)
- [Mongo with Quarkus](quarkus-mongo/README.md)

## TODO
These are the things I want to test with Quarkus.
- [x] Run with Java 11, test with JUnit5 + RestAssured 
- [ ] Remote developer mode (hot reload in container)
- [x] JAX-RS : Simple API with JSON (JSON-B)
- [x] JAX-RS : Simple API with XML (JAX-B)
- [x] JAX-RS : ExceptionMapper
- [x] JSON-P : JsonPatch
- [x] CDI : Scoped Injection
- [x] CDI : Using @Alternative implementations for mocking
- [x] MicroProfile configuration
- [x] MicroProfile OpenAPI (annotating with own documentation)
- [x] MicroProfile Health (with custom health check)
- [x] MicroProfile Metrics (with custom metrics)
- [x] MicroProfile JWT (with Keycloak)
- [x] MicroProfile TypeSafe REST Client
- [ ] MicroProfile FaultTolerance
- [x] MicroProfile Reactive Messaging & Streams (using Kafka extension)
- [x] Add SwaggerUI start page at http://localhost:8080/
- [x] MongoDB (needs a MongoDB running on default port)
- [x] Dockerfile for executable jar (openjdk11)
- [x] Native executable in Docker container (needs Java 8)
- [ ] Native executable using GraalVM on MacOS (worked earlier using java 8)
- [x] Extension : Scheduler (triggers Kafka messages)
- [x] Extension : Panache (database using Panache with JTA and JPA/JDBC)
- [x] Extension : Camel
- [ ] Extension : Kotlin
- [x] Extension : Mailer
- [x] Extension : Validation
- [x] Extension : Kafka (Reactive Messaging & Streams)
- [x] Extension : Vert.x (EventBus and Async Messaging between beans)
- [ ] Extension : Reactive Postgres Client
- [x] Others : Uploading multipart/form-data using JAX-RS (with RestEASY MultipartFormDataInput plugin)
- [ ] Bug : UsersResourceTest is disabled (does not run when quarkus-smallrye-openapi is included)

## Some comments ...
> Doesn't require JAX-RS application (@ApplicationPath) <br/>
> Doesn't require @Inject on config (@ConfigProperty) <br/>
> Config using resources/application.properties or resources/META-INF/microprofile-config.properties
> Web pages in META-INF/resources (no webapp) <br/>
> Debugger works nice from IntelliJ (`Run -> Attach to Process`) <br/>

## Pre-requisite ...
I'm using Open JDK 11 with Maven 3.5 and MongoDB + Kafka running on default ports. 
```
$ java -version
openjdk version "11.0.2" 2019-01-15
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.2+9)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.2+9, mixed mode)

$ mvn -version
Apache Maven 3.5.4 (1edded0938998edf8bf061f1ceb3cfdeccf443fe; 2018-06-17T20:33:14+02:00)
Maven home: /usr/local/Cellar/maven/3.5.4/libexec
```

## Create a new empty project
Create a new folder and run the command in there.
```
mvn io.quarkus:quarkus-maven-plugin:0.16.1:create
```

## Note on MP-JWT and groups claim
The MP-JWT decodes the JWT and use the `groups` claim to select `roles` for RBAC.  This mean that you 
actually need a `group` claim  in the JWT token even if you do not actually use @RolesAllowed for RBAC
in you code.  If your JWT doesn't contain `groups` claim - Quarkus will throw a Null Pointer Exception.

If you use KeyCloak as your OIDC server your `groups` are *not* mapped into claims by default.  You
can manually add a client mapper for "User Realm Role" into "Token Claim Name" `groups` (for access-tokens when
using service accounts) or you  could map the "Group Membership" (when using ID-tokens and direct access grants).

Get Access Token (using Service Account)
```
MYCLIENTCREDENTIALS = base64("client_id:client_secret")

curl -X POST 'https://localhost:9443/auth/realms/quarkus/protocol/openid-connect/token' \
-H "Content-Type: application/x-www-form-urlencoded" \
-H "Authorization: Basic MYCLIENTCREDENTIALS" \
-d 'grant_type=client_credentials' \
| jq -r '.access_token'
```

Get ID Token (using Direct Access Grants)
```
curl -X POST 'https://localhost:9443/auth/realms/quarkus/protocol/openid-connect/token' \
-H "Content-Type:application/x-www-form-urlencoded" \
-d "scope=openid" -d "grant_type=password" \
-d "client_id=CLIENTID" -d "client_secret=CLIENTSECRET" \
-d "username=USERNAME" -d "password=PASSWORD" \
| jq -r '.id_token'
```

## URL's to check out
Our own welcome html page :
- SwaggerUI : http://localhost:8080/

A number of built-in pages :
- SwaggerUI : http://localhost:8080/swagger-ui
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
# clean and remove old artificts
mvn clean

# compile and run in dev mode (hot reload and debugging by default on port 5005)
mvn compile quarkus:dev

# build from scratch and run all tests
mvn clean test

# create runnable artifacts (SwaggerUI on http://localhost:8080/)
mvn clean package
java -jar target/quarkus-1.0.0-SNAPSHOT-runner.jar

# list all Quarkus extensions that can be used in the pom.xml
mvn quarkus:list-extensions
```

## Running in Docker
Run the following from the root of this project.
> NOTE : The `mongodb.url` must point to the host running MongoDB (localhost is inside container and cannot be used)
```
mvn clean package

docker build -t my-java-app .
docker run -it --rm --name my-running-app -p 8080:8080 -e mongodb.url=mongodb://192.168.0.2:27017 my-java-app

open http://localhost:8080/
```

## Running as Native code in Linux Container
Using the swd847/centos-graal-native-image-rc13:latest docker image to build native linux binary.

Before we can build, edit the `pom.xml` and use `1.8` as the Java version instead of `11`.
We also need to skip test when building as some use Java 11 features.
```
mvn clean package quarkus:native-image -Dnative-image.docker-build=true -Dmaven.test.skip=true
```
Then build docker image with linux binary and path to SunEC native HTTPS libaray.
```
docker build . -t native-test -f Dockerfile.native-code
```
Now you can run a new Docker container with the binary.
```
docker run --rm -p 8080:8080 -e hello.message=RunningNative -e mongodb.url=mongodb://192.168.0.8:27017 -e userservice.url=https://jsonplaceholder.typicode.com/ native-test
```

## Running as Native code on MacOS
Quarkus is built to support Ahead Of Time compilation and running on the GraalVM / SubstrateVM.
This is why the ArC CDI implemtation is not 100% CDI spec compliant.

I used `graalvm-ce-1.0.0-rc14` and Quarkus 0.12.0 to compile an earlier version of this project on my iMac with Java 8.
```
# make sure GraalVM is installed
teedjay$ java -version
openjdk version "1.8.0_202"
OpenJDK Runtime Environment (build 1.8.0_202-20190206132754.buildslave.jdk8u-src-tar--b08)
OpenJDK GraalVM CE 1.0.0-rc14 (build 25.202-b08-jvmci-0.56, mixed mode)

# make sure $GRAALVM_HOME is set correctly
teedjay$ echo $GRAALVM_HOME
/Library/Java/JavaVirtualMachines/graalvm-ce-1.0.0-rc14/Contents/Home

# NOTE : set java versions in pom.xml back to 1.8 before compiling
# NOTE : some tests use Java 11 features - make sure to skip tests
teedjay$ mvn clean package quarkus:native-image -Dmaven.test.skip=true

teedjay$ ./target/quarkus-1.0.0-SNAPSHOT-runner
2019-03-24 21:16:49,095 INFO  [io.quarkus] (main) Quarkus 0.12.0 started in 0.006s. Listening on: http://[::]:8080

teedjay$ java -jar target/quarkus-1.0.0-SNAPSHOT-runner.jar
2019-03-24 21:26:47,600 INFO  [io.sma.ope.api.OpenApiDocument] (main) OpenAPI document initialized: io.smallrye.openapi.api.models.OpenAPIImpl@43c1b556
2019-03-24 21:26:48,128 INFO  [io.quarkus] (main) Quarkus 0.12.0 started in 0.729s. Listening on: http://[::]:8080
2019-03-24 21:26:48,131 INFO  [io.quarkus] (main) Installed features: [cdi, resteasy, resteasy-jsonb, smallrye-health, smallrye-metrics, smallrye-openapi]

```
