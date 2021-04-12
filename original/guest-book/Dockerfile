FROM maven:3.6.3-openjdk-11-slim as build

WORKDIR /opt/app

COPY pom.xml .
RUN mvn clean dependency:go-offline

COPY src ./src
RUN mvn clean install

FROM openjdk:11-slim as runtime

WORKDIR /opt/app

COPY --from=build /opt/app/target/*.jar /opt/app/app.jar

EXPOSE 3000

CMD [ "java", "-jar", "app.jar" ]
