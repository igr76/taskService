FROM openjdk:17
ADD /target/TaskManagement-0.0.1-SNAPSHOT.jar TaskManagement-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","TaskManagement-0.0.1-SNAPSHOT.jar"]
