### STAGE 1: Build ###
FROM gradle:jdk11 AS build
COPY --chown=gradle:gradle ./ /home/gradle/src/workmotion
WORKDIR /home/gradle/src/workmotion

RUN gradle assemble

### STAGE 2: Run ###
FROM adoptopenjdk/openjdk11:alpine-slim

EXPOSE 8080

COPY --from=build /home/gradle/src/workmotion/build/libs/*.jar /opt/app/workmotion.jar

ENTRYPOINT ["java","-jar","/opt/app/workmotion.jar"]