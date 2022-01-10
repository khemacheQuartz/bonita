FROM openjdk:11-jdk-slim as build

WORKDIR /work
COPY gradle/ gradle/
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN ./gradlew installBootDist

FROM openjdk:11-jdk-slim
WORKDIR /work
LABEL "team"="Bonita"

COPY --from=build /work/build/install/bonita-boot ./
CMD /work/bin/bonita