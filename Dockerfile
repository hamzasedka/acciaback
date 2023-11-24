FROM maven:3.8-openjdk-11-slim
# FROM maven:3.5.0-jdk-8

# Set the working directory in the container
WORKDIR /app/accia_back

# Copy the source code and pom.xml to the container
COPY . .

# Expose the port that the app will run on
EXPOSE 8080

# Run the Spring Boot application using mvn spring-boot:run
CMD ["mvn", "spring-boot:run"]
