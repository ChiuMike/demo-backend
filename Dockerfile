FROM openjdk:17-jdk
COPY target/webapp.jar aws-demo-backend.jar
ENTRYPOINT ["java","-jar","/aws-demo-backend.jar"]