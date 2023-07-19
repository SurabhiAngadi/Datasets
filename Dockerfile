FROM openjdk:11
EXPOSE 8080
COPY target/datasetProj-0.0.1-SNAPSHOT.jar datasetProj-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/datasetProj-0.0.1-SNAPSHOT.jar"]