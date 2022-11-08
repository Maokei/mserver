FROM eclipse-temurin:17-jre-alpine
VOLUME /main-app
ADD build/libs/mserver-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]

#FROM gradle:jdk17-alpine AS BUILD
#WORKDIR /build
#COPY .gradle/ .gradle
#COPY build.gradle settings.gradle ./

#COPY src ./src
#RUN ls /tmp
#RUN gradle build -x test

#FROM eclipse-temurin:17-jre-alpine
#EXPOSE 8080
#WORKDIR /app

##RUN ls /tmp
##COPY --from=BUILD /tmp /tmp
#COPY --from=build /build/build/libs/mserver-0.0.1-SNAPSHOT.jar /app/mserver-0.0.1-SNAPSHOT.jar
#CMD ["java", "-jar", "mserver-0.0.1-SNAPSHOT.jar"]