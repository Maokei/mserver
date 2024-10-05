# MServer

## Requirements
- Java21
- Docker

## Build
```
./gradlew tasks
./gradlew build
# Without running tests
./gradlew build -x test
```

## Run with java
```
mserver/mserver/
./gradlew bootRun
```

## Run with docker
```
docker build -t mserver .
docker run --rm -it $(docker build -q .)
docker run -e "SPRING_PROFILE_ACTIVE=docker" -p 8080:8080 --network mserver_our-net -it mserver
docker run -e "SPRING_PROFILE_ACTIVE=docker" -p 8080:8080 --network mserver_our-net --rm -it $(docker build -q .)

# multi stage Dockerfile
docker build -t mserver -f Dockerfile-multi .

docker run --rm -it $(docker build -q -f Dockerfile-multi .)
```

## Run with docker-compose
```
docker-compose up
docker-compose -f docker-compose.prod.yml up
```

## Swagger
Swagger generated documentation:
`http://localhost:8080/swagger-ui.html`

## Testing page
Browse too `http://localhost:8080`

## Media
Two possible requests
- Status: 200 OK.
- Status: 206 Partial Content.