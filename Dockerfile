FROM tomcat:9.0.58-jdk17-openjdk-slim
VOLUME /tmp
COPY target/HSignzBaseAppServices.war ${CATALINA_HOME}/webapps/
EXPOSE 8080
ENTRYPOINT ["catalina.sh", "run"]