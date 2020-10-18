FROM openjdk:8
WORKDIR /rulette/app
COPY . /rulette/app
CMD ["java","-Dserver.port=${PORT}","-jar","build/libs/Clean-Code.jar"]
