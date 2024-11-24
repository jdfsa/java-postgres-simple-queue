#
# Build stage
#
FROM gradle:8.11.1-jdk21 AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
COPY ./app/src/ $HOME/src
COPY ./app/build.gradle $HOME
RUN gradle clean build

#
# Package stage
#
FROM openjdk:21
COPY --from=build /usr/app/build/libs/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT java -jar /app/app.jar
