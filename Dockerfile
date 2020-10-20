FROM openjdk:11.0.7-jre-slim
ENV TZ Brazil/East
RUN apt-get update -y && apt-get upgrade -y
RUN apt-get install tzdata curl -y && ln -sf /usr/share/zoneinfo/Brazil/East /etc/localtime

COPY build/libs/*.jar /app/school.jar

ENTRYPOINT ["java", "-jar","/app/school.jar"]