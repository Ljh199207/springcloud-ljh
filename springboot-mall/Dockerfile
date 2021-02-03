FROM java:8
#openjdk:adoptopenjdk/openjdk11
VOLUME /springboot/es
ADD target/springboot-elasearch-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 80
RUN bash -c "touch /app.jar"
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
  && echo 'Asia/Shanghai' >/etc/timezone \
