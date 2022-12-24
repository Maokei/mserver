# MServer

## Requirements
- Java17
- Docker

## Run with java
```
mserver/mserver/
./gradlew bootRun
```

## Run with docker
```
docker run --rm -it $(docker build -q .)
# multi stage Dockerfile
docker run --rm -it $(docker build -q -f Dockerfile-multi .)
```

## Run with docker-compose
```
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