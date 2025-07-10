# Use Eclipse Temurin OpenJDK image for Java 21
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Maven files and download dependencies
COPY pom.xml .
RUN apt-get update && apt-get install -y maven && mvn dependency:go-offline

# Copy all source files
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Run the jar
CMD ["java", "-jar", "target/ResilientEmailServiceSpringBoot-1.0.0.jar"]
