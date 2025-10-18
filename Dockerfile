# Dockerfile

FROM maven:3.8.7-eclipse-temurin-17 AS build 
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline  
COPY . .
RUN mvn clean install -DskipTests
FROM eclipse-temurin:17-jre-focal 
WORKDIR /usr/app
COPY --from=build /app .
ENTRYPOINT ["echo", "Imaginea Docker a fost construită cu succes!"]