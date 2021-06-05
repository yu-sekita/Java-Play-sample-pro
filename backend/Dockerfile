FROM openjdk:8-jdk

ARG SBT_VERSION

# Envs
ENV SBT_VERSION ${SBT_VERSION:-1.3.7}

# Update
RUN apt-get update && \
    apt-get upgrade -y

# Install sbt
RUN curl -L -o sbt-$SBT_VERSION.deb https://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
    dpkg -i sbt-$SBT_VERSION.deb && \
    rm sbt-$SBT_VERSION.deb && \
    apt-get update && \
    apt-get install sbt

COPY ./ /server
WORKDIR /server

RUN sbt about
