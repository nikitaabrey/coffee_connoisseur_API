FROM adoptopenjdk
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY target/*.jar app.jar
EXPOSE 443
CMD ["java","-jar","app.jar"]