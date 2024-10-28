FROM tomcat:9.0.73-jdk17
RUN echo $HOME
RUN mkdir /db
COPY Auctooze.war /usr/local/tomcat/webapps/
WORKDIR /db
EXPOSE 8080
CMD ["catalina.sh", "run"]