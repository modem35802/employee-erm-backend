FROM openjdk:17-jdk-alpine
EXPOSE 8080
ADD target/springboot-mysql-docker.jar springboot-mysql-docker.jar
ENTRYPOINT ["java", "-jar", "/springboot-mysql-docker.jar"]
