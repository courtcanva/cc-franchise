FROM openjdk:17.0.2 AS build

RUN microdnf install findutils

WORKDIR /workspace/app
COPY . /workspace/app

RUN ./gradlew clean build

FROM openjdk:17.0.2
VOLUME /tmp

ARG VERSION=0.0.1-SNAPSHOT
ARG CC_FRANCHISE_DIR=/workspace/app/build/libs
COPY --from=build ${CC_FRANCHISE_DIR}/cc-franchise-${VERSION}.jar app.jar

ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]