FROM java:8-jdk-alpine

EXPOSE 8004

COPY ./target/catalog-service-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch catalog-service-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","catalog-service-0.0.1-SNAPSHOT.jar"]