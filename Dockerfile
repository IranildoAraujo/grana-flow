FROM maven:3.9.5-eclipse-temurin-17 AS builder
WORKDIR /app

COPY pom.xml .

COPY ./src ./src

RUN mvn dependency:go-offline

RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/grana-flow.jar

ARG APP_NAME
ARG CONTEXT_PATH
ARG CORS_ORIGIN
ARG DS_DRIVER_CLASS_NAME
ARG DS_URL
ARG DS_USERNAME
ARG DS_PASSWORD
ARG DS_SCHEMA
ARG DS_DIALECT
ARG PROFILES_ACTIVE
ARG SECRET_KEY
ARG TZ
ARG ORACLE_PASSWORD
ARG ORACLE_SID

ENV APP_NAME=${APP_NAME} \
    CONTEXT_PATH=${CONTEXT_PATH} \
    CORS_ORIGIN=${CORS_ORIGIN} \
    DS_DRIVER_CLASS_NAME=${DS_DRIVER_CLASS_NAME} \
    DS_URL=${DS_URL} \
    DS_USERNAME=${DS_USERNAME} \
    DS_PASSWORD=${DS_PASSWORD} \
    DS_SCHEMA=${DS_SCHEMA} \
    DS_DIALECT=${DS_DIALECT} \
    PROFILES_ACTIVE=${PROFILES_ACTIVE} \
    SECRET_KEY=${SECRET_KEY} \
    TZ=${TZ} \
    ORACLE_PASSWORD=${ORACLE_PASSWORD} \
    ORACLE_SID=${ORACLE_SID}

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/grana-flow.jar"]