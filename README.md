# Alten Assignment


## A brief
This assignment is an Microservices architecture assignment which present status of connected vehicles.
In this assignment I use Spring Boot and Spring Cloud as framework. This assignment uses Docker and Kubernetes to containerized the application and deployed to GCP.
I also used Thymeleaf as template engine and H2 database as in memory database.
Netflix Eureka is used as REST based service for Service Discovery and Load Balancing.


## Analysis
The following are REST services designed for this assignment:

* Catalog Service
* Data Service
* Simulator Service
* Vehicle Info Manager Service
* Service Discovery (Eureka)

### Catalog service
This service is using Thymeleaf as template engine and has a controller to get list of all vehicles, vehicles with a specific status or vehicles belong to a customer


### Data service
It is using H2 database with a schema and predefined data. Spring Data JPA is used in this service for CRUD operation (predefined and customized queries).


### Simulator service
This service is simulating vehicles with their status and their reachability. It has static data of vehicles and has a controller to simulate pinging vehicles. It also sends vehicles status to data service every 60 seconds to update it if it has changed.
Vehicles will be out of reach (not able to respond to ping) randomly so that it can be compared with their status to update the vehicle status accordingly.


### Vehicle Info Manager service
The purpose of this service is to ping vehicles in the Simulator Service and compare it with the status of the vehicle in the Data Service. If the Vehicle is not responding to the ping or its status has changed then it will send request to Data Service to update status of the Vehicle.


### Vehicle Info Manager service
This service is enabling service discovery for this application. All services will register into this service so that they can be discovered and reached dynamically without hard coding their host name and port.


## Deployment
This application is using Docker and Kubernetes to containerized the services and deploy them into GCP (used in this assignment) or other cloud solutions.
Every Service has its own Dockerfile and docker-compose.yml file is also provided to ease the process.
alten-deployment.yml and alten-service.yml files are also provided to register images into cluster.


## How to run the Application
The following steps are required to run this application:
1. Download the source code from this repository: https://github.com/HNShad/alten-assignment
2. All the above Services modules needs to be built in order to generate jar file. Here is the command:
```shell
mvn clean install
```
3. Now it's time to create your cluster on the cloud service. I used GCP for the purpose of this assignment.
Here is the cluster setup:
  * Name: standard-cluster-1
  * Zone: us-central1-a
  * Number of Node Pools: 3
  * CPU: 1
  * Memory: 3.75

4. Every Service module has a folder and it contains a Dockerfile. This Dockerfile is to generate docker image respective to its own Service. I specify the image name I used for each Service module so that it'll be match with image names in the yaml files. I'm using gcp.io for the purpose of this assignment
```shell
docker image build -t {image name} .
```

* gcr.io/big-unison-241706/service-discovery
* gcr.io/big-unison-241706/data-service
* gcr.io/big-unison-241706/simulator-service
* gcr.io/big-unison-241706/catalog-service
* gcr.io/big-unison-241706/infomanager-service

5. Run the following command for every of above images to register them into cloud registry:
```shell
gcloud docker -- push {image name}
```
6. Run the following command to run the Kubernetes deployment manifest.
```shell
kubectl apply -f alten-deployment.yaml
```
7. Run the following command to run the Kubernetes service manifest and run the services
```shell
kubectl apply -f alten-service.yaml
```
8. Run the following command to an IP assigns to your services.
```shell
kubectl expose deploymen altenapp --type="LoadBalancer"
```
9. Following commands lets you verify if the pods, deployment or services are healthy and running:

```shell
kubectl get pods
kubectl get deployment
kubectl get service
```
10. Once the IP address assigns to catalog service, use the IP address add it to the following url and hit the application because it's waiting for you to run it. ;)
```shell
 {IP address}:8004
```
