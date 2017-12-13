FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/springboot-testcontainers-snip-1.0-SNAPSHOT.jar app-snip.jar
RUN sh -c 'touch /app-snip.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "java","-Djava.security.egd=file:/dev/./urandom","-jar","/app-snip.jar" ]