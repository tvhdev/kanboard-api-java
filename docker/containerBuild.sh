#!/bin/bash
DOCKER_PROJECT="kanboard"
KANBOARD_VERSION="1.0.41"
DOCKER_CONTAINER_KANBOARD="${DOCKER_PROJECT}_test"
DOCKER_CONTAINER_MAVEN="${DOCKER_PROJECT}_maven"
DOCKER_IMAGE_KANBOARD="$DOCKER_CONTAINER_KANBOARD:$KANBOARD_VERSION"
DOCKER_IMAGE_MAVEN="$DOCKER_CONTAINER_MAVEN"

# check volumes 
echo "--- checking volumes"
if [[ ! -d "./_volumes" ]]; then
    echo "folder missing: ./_volumes"
    exit 1
fi

echo "change permissions on volumes"
find ./_volumes/* -maxdepth 0 -type d -exec chown "$(id -u):$(id -g)" {} ";"
find ./_volumes/* -maxdepth 0 -type d -exec chmod 777 {} ";"

# check windows
if [[ $(uname -s | grep MINGW64_NT | wc -l) -eq 1 ]]; then
    echo "converting files for Windows"
    find . -name 'mvnw' -type f -exec git checkout {} ";"
    find . -name 'mvnw' -type f -exec dos2unix {} ";"
    find . -name 'start.sh' -type f -exec git checkout {} ";"
    find . -name 'start.sh' -type f -exec dos2unix {} ";"
fi

# set write permissions if not default user (id 1000)
if [[ $(uname -s | grep MINGW64_NT | wc -l) -ne 1 ]]; then
    if [[ $(id -u) -ne 1000 ]]; then
        chown 1000:1000 ../project -R
        chown 1000:1000 ../docker -R
    fi
fi

docker build -t $DOCKER_IMAGE_KANBOARD ./kanboard
docker build -t $DOCKER_IMAGE_MAVEN ./maven
docker run -d --name  $DOCKER_CONTAINER_KANBOARD $DOCKER_IMAGE_KANBOARD
exit 0
