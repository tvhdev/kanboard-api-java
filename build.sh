#!/bin/bash
DOCKER_PROJECT="kanboard"
DOCKER_CONTAINER_KANBOARD="${DOCKER_PROJECT}_test"
DOCKER_CONTAINER_MAVEN="${DOCKER_PROJECT}_maven"
DOCKER_IMAGE_MAVEN="$DOCKER_CONTAINER_MAVEN"

find ./docker -name '*.sh' -type f -exec chmod a+x {} ";"
cd docker
./containerBuild.sh

docker run --rm -ti \
        --volume $PWD/../project:/project \
        --volume $PWD:/docker \
        --volume $PWD/_volumes/maven_m2:/home/ubuntu/.m2 \
        --hostname maven \
        --name $DOCKER_CONTAINER_MAVEN \
        --link $DOCKER_CONTAINER_KANBOARD:kanboard \
        $DOCKER_IMAGE_MAVEN

./containerDestroy.sh

echo -e "\e[32m\n\njar location ./project/target/kanboard-api-java.jar\n\e[39m"
exit 0
