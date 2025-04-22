FROM amazoncorretto:17-alpine-jdk

COPY target/TallerDespliegue-0.0.1-SNAPSHOT.jar /api-taller-despliegue.jar

ENTRYPOINT ["java", "-jar", "/"]