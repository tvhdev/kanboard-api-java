FROM openjdk:8-jdk

VOLUME ["/project" "/root/.m2"]
WORKDIR /project

# do nothing loop
CMD touch /test && tail -f /test
