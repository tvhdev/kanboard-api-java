---
type: development
lang: en
permalink: /development/
---

# Development

{% include navigation.md %}

## Quick start

Make sure you have a working [Docker](https://docs.docker.com/engine/installation/) and
[docker-compose](https://docs.docker.com/compose/install/) environment.

```bash
# download
git clone https://github.com/phoen1x/kanboard-api-java.git
cd kanboard-api-java

# start project
docker-compose up -d

# run integration tests
docker-compose exec maven ./mvnw -Dtest=Kanboard\*\*IntegrationTest test

# build jar file to project/target/kanboard-api-java.jar
docker-compose exec maven ./mvnw clean package

# show build results integration test - login admin:admin
xdg-open http://172.19.1.1:81/

# stop project
docker-compose down
```

## Maven (build only)

```bash
cd project
./mvnw clean package
```
