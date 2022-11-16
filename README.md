# Challenge BPD - Taxi24 API

## Introduction

This application is a Rest API. It serves as a core component for the driver, passenger and trip models.
The application uses a non-relational database (MongoDB) and is developed with Java and Spring Boot.

## About the code

The application is made from scratch. The technology stack is Java with [Spring Boot](https://spring.io/projects/spring-boot).

The project uses the following dependencies (libraries), fundamental for the solution:

- [**J**DK 17](https://openjdk.org/projects/jdk/17/): Runtime environment
- [**M**aven](https://maven.apache.org/): Project management
- [**S**pring Boot](https://spring.io/projects/spring-boot): Backend framework
- [**M**ongoDB](https://www.mongodb.com/): Database

## Setting

### Requirements

1. [JDK 17](https://openjdk.org/projects/jdk/17/) installed.
2. [Maven >=3.8.6](https://maven.apache.org/) installed.
3. [Docker](https://www.docker.com/) installed.

## Running the application

1. Start Docker compose to get the database ready to be used by the api

```sh
$ docker-componse up
```

2. Run the api

```sh
$ ./mvnw spring-boot:run
```

### Live app monitoring

The app has an endpoint that respond to an http status code: `200` to monitor if it is alive.

> `GET:` [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

### Initial Data

When you will run the application, basic test data for the driver and passenger collections will be created at start-up.

### Manual testing

In the folder `src/main/resources` you will find the file `bpd.postman_collection.json` that you can import into [Postman](https://www.postman.com/) to test the api.

#### Checking the requirements

###### Get a list of all drivers
**GET**: `/drivers`

###### Get a list of all available drivers
**GET**: `/drivers/status/:status` > /drivers/status/ONLINE

###### Get a list of all available drivers within a 3km radius for a specific location
**GET**: `/drivers/:longitude/:latitude/:kmDistance` > /drivers/-34.61115923466013/-58.370180534907114/3

###### Get a specific driver by ID
**GET**: `/drivers/:id` > /drivers/63751b55dd754c1a6cfd92d3

###### Create a new "Trip" request by assigning a driver
**POST**: `/trips`

###### Complete a trip
**PUT**: `/trips/:id/status/:status` > /trips/637535188a37c56ce23bb073/status/TRIP_COMPLETED

###### Get a list of all active trips
**GET**: `/trips/status/:status` > /trips/status/ON_TRIP

###### Get a list of all passengers
**GET**: `/passengers`

###### Get a specific passenger by ID
**GET**: `/passengers/:id` > /drivers/63751b55dd754c1a6cfd92d5

###### Get a list of the 3 closest drivers to a starting point
**GET**: `/drivers/:longitude/:latitude` > /drivers/-34.61115923466013/-58.370180534907114
