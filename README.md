# Rabobank Branches Management

Rabobank Branch API details are documented here. 

### Technology Stack

* Java 17
* Spring Boot 2.7.17
* Gradle 7.3.3+
* MongoDB 7.0

### Build

```shell script
./gradlew clean build
```
### Run

```shell script
docker-compose up
```

### Usage
API is served on localhost with port 8080 and with context path /api.
The API provides the following endpoints:
- POST/branches - Opens a new branch

*The request and response bodies for each endpoint are defined in the dto package.