# Stage 1: Build the .war file using Maven
FROM maven:3.8-openjdk-17 AS build

# Set the working directory for the build
WORKDIR /app

# Copy the pom.xml file first to leverage Docker's layer caching.
# This way, dependencies are only re-downloaded if pom.xml changes.
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of your application's source code
COPY src ./src

# Build the application and create the .war file
# This command skips running tests, which is common for Docker builds.
RUN mvn clean package -DskipTests


# Stage 2: Create the final image using Tomcat
FROM tomcat:9.0-jdk17-temurin

# Remove any default apps from Tomcat's webapps directory
RUN rm -rf /usr/local/tomcat/webapps/*

# From the 'build' stage, copy the .war file that was created.
# You might need to change 'WebApp.war' if your artifactId in pom.xml is different.
COPY --from=build /app/target/WebApp.war /usr/local/tomcat/webapps/ROOT.war

# Expose the port Tomcat runs on
EXPOSE 8080

# The command to start the Tomcat server
CMD ["catalina.sh", "run"]