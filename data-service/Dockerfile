FROM java:8-jdk-alpine

EXPOSE 8001

COPY ./target/data-service-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch data-service-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","data-service-0.0.1-SNAPSHOT.jar"]