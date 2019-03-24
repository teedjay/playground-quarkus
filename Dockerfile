FROM openjdk:11
RUN mkdir -p /usr/src/myapp
COPY ./target/quarkus-1.0.0-SNAPSHOT-runner.jar /usr/src/myapp
COPY ./target/lib /usr/src/myapp/lib
WORKDIR /usr/src/myapp
CMD ["java", "-jar", "quarkus-1.0.0-SNAPSHOT-runner.jar"]

# NOTE the mongodb.url must point to the host running MongoDB (localhost is inside container and cannot be used)
#
# mvn clean package
#
# docker build -t my-java-app .
# docker run -it --rm --name my-running-app -p 8080:8080 -e mongodb.url=mongodb://192.168.0.2:27017 my-java-app
#
# open http://localhost:8080/