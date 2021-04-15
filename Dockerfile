#
# Build stage
#
FROM maven:3-adoptopenjdk-11-openj9 AS builder

ARG SKIP_TEST=true

WORKDIR /build
COPY pom.xml .
#Copy source code
COPY src ./src


# Build application
RUN mvn -Dmaven.test.skip=$SKIP_TEST package

#
# Package stage
#
FROM adoptopenjdk/openjdk11-openj9:alpine-jre

WORKDIR /opt/app

COPY --from=builder /build/target/*.jar app.jar

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    START_SLEEP=0 \
    JAVA_OPTS=""

EXPOSE 8080

CMD java ${JAVA_OPTS} -Dsun.misc.URLClassPath.disableJarChecking=true \
                      -Djava.io.tmpdir=/var/tmp \
                      -Djava.security.egd=file:/dev/./urandom \
                      -jar app.jar
