# Task-Management
This application is webservise which accept HTTP call for CRUD tasks. API is responsible for managing and storing in H2 database tasks.
Application is using Java 11, Spring Boot, Spring Data JPA, Maven, Lombok, H2, Swagger

## Installation
In the beggining of instalation you need to install java 11+ and maven

[Maven](https://maven.apache.org/download.cgi)

[Java 11](https://adoptopenjdk.net/)


## Running the service 
First step in clone the Git repository:

$ git clone https://github.com/AnnaPostek/Task-Management
$ cd Note

Once the clone is done, run below

$ mvn clean install

$ mvn spring-boot:run

## Web
Application is available on below localhost with port 8080

## Available Endpoints
After run application you can check on http://localhost:8080/swagger-ui/ which endpoints are available
![image](https://user-images.githubusercontent.com/56793192/159158695-ed2bfaa8-298c-4a39-ace4-f863750f944d.png)

## Examples usages
It is possible for testing API in Swagger, Postman, curl and endpoints with GET Mapping directly on browser
