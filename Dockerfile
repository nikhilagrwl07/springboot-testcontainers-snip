FROM openjdk:8
VOLUME /tmp
ADD target/springboot-testcontainers-snip-1.0-SNAPSHOT.jar app-snip.jar
RUN sh -c 'touch /app-snip.jar'
ENV JAVA_OPTS=""
EXPOSE 8090
ENTRYPOINT [ "java","-Djava.security.egd=file:/dev/./urandom","-jar","/app-snip.jar" ]