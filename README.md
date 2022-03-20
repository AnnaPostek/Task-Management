# Task-Management
This application is webservise which accept HTTP call for CRUD tasks. For one task can be added more that one user. 

API is responsible for managing and storing in H2 database tasks.
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

## Login to database
Database H2 in memory is available on below localhost:
http://localhost:8080/h2-console

add date according following:
jdbc url => jdbc:h2:mem:testdb

UserName => sa

![image](https://user-images.githubusercontent.com/56793192/112542027-5f740200-8db4-11eb-9bd9-47f63cd7cc7c.png)

click Connect and after that you are connecing to database.

on database loading initial data according below:

![image](https://user-images.githubusercontent.com/56793192/159184928-a877f6b0-9db8-4fcd-947c-9b439a903d56.png)

![image](https://user-images.githubusercontent.com/56793192/159184907-c31faa70-508e-4453-bf5e-1d92d46da621.png)

## Examples usages
It is possible for testing API in Swagger, Postman, curl and endpoints with GET Mapping directly on browser

Let me show some of examples:

### Add task in Swagger: 
click on task-controller then POST and Try it out, fill the values (require value is only title) and click Execute. 

![image](https://user-images.githubusercontent.com/56793192/159184258-9c2f6a13-9e91-42b3-bd3b-b99927ccb0de.png)

Task is added to database.

![image](https://user-images.githubusercontent.com/56793192/159184316-c0bfc680-4cd6-4111-ae1d-dbf628b517c3.png)

## Delete task
Below is example of usage delete endpoint on Postman

![image](https://user-images.githubusercontent.com/56793192/159184426-d994f4df-7c5a-47c5-8580-523afb938383.png)

## Get task and Get all users
Below is example of usage task and list of users directly on browser:

http://localhost:8080/task/6

![image](https://user-images.githubusercontent.com/56793192/159184619-d4b1eb3b-4a52-479b-af61-e48581421e4d.png)

http://localhost:8080/users

![image](https://user-images.githubusercontent.com/56793192/159184572-9f34564d-53af-4ee5-a375-5cd0c44087b1.png)

## Update task
Below is example of usage update task endpoint in Curl

curl -X PUT "http://localhost:8080/task/5" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"deadline\": \"2022-03-20 20:30:00\", \"description\": \"string\", \"status\": \"IN_PROGRESS\", \"title\": \"string\", \"users\": []}"


Similarly tests are possible with User Controller.
