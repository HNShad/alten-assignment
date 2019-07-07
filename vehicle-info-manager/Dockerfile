FROM java:8-jdk-alpine

EXPOSE 8003

COPY ./target/vehicle-info-manager-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch vehicle-info-manager-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","vehicle-info-manager-0.0.1-SNAPSHOT.jar"]