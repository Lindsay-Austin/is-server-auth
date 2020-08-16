FROM java:8
VOLUME /tmp
ADD servicea-0.0.1.jar servicea.jar
RUN bash -c 'touch /servicea.jar'
EXPOSE 8762
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/servicea.jar" ]
