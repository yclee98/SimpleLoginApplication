FROM openjdk:17

WORKDIR /usr/app

COPY ./Backend/simplelogin/target/backend_login_app.jar .

EXPOSE 8080

ENTRYPOINT ["java","-jar","backend_login_app.jar"]