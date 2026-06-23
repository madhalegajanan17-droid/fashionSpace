# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application using Tomcat 10.1 (supports Jakarta EE 10 / Servlet 6.0)
FROM tomcat:10.1-jdk21
# Remove default Tomcat webapps
RUN rm -rf /usr/local/tomcat/webapps/*
# Copy the compiled WAR file to the ROOT of Tomcat
COPY --from=build /app/target/fashionspace-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
