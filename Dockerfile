# Use an OpenJDK image as the base image for the build stage
FROM adoptopenjdk AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project file and download dependencies
COPY ./pom.xml .
RUN mkdir /target
RUN apt-get update && apt-get install -y maven && mvn -B dependency:go-offline

# Copy the application source code
COPY ./src ./src

# Build the application
RUN mvn -B clean package -DskipTests

# Use a smaller base image for the runtime stage
FROM openjdk:17-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port that your Spring Boot application will run on
EXPOSE 8080

# Specify the command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]
