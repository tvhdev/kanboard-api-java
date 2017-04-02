FROM ubuntu:16.04
#FROM armv7/armhf-ubuntu:16.04

# base install
RUN \
        apt-get update \
    &&  DEBIAN_FRONTEND=noninteractive apt-get install -y \
                apt-utils \
    &&  apt-get update \
    &&  DEBIAN_FRONTEND=noninteractive apt-get install -y \
                vim \
                curl \
                unzip

# Set locales
RUN locale-gen en_US.UTF-8 && \
    update-locale LANGUAGE=en_US:en LANG=en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US.UTF-8
ENV LC_CTYPE en_US.UTF-8
ENV LC_ALL en_US.UTF-8

# install java
RUN \
        echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections \
    &&  echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee /etc/apt/sources.list.d/webupd8team-java.list \
    &&  apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886 \
    &&  apt-get update \
    &&  apt-get install -y oracle-java8-installer \
    &&  apt-get install -y oracle-java8-set-default

# create user
RUN     groupadd ubuntu --gid 1000 \
    &&  useradd ubuntu -s /bin/bash -m -G sudo --gid 1000 --uid 1000 \
    &&  echo 'ubuntu:ubuntu' |chpasswd

COPY ./start.sh /start.sh

VOLUME ["/project", "/docker", "/home/ubuntu/.m2"]

USER ubuntu
WORKDIR /project

CMD /bin/bash /start.sh
