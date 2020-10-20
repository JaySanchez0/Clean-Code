 # Clean Code - Masiv
 
 #### Present By:
 
 Jeisson G. Sanchez R. 
 
 
 ### Requirements
 
 - Java JDK
 - Gradle
 - DB Redis (We recommend using a database provided by redis labs free account).
 
 ### Test
 
 [![CircleCI](https://circleci.com/gh/JaySanchez0/Clean-Code.svg?style=svg)](https://circleci.com/gh/JaySanchez0/Clean-Code)
 


### Docker Hub

[ver](https://hub.docker.com/r/jsanchez0/roulette-java)


### Docker build

~~~
    docker build --tag name_image .
~~~

### Execute the docker image

~~~
    docker run -e PORT=container_port -e REDISHOST=url_redis -e REDISPORT=port_redis -e REDISPASSWORD=redis_password -p machine_port:container_port jsanchez0/roulette-java
~~~

is required update all variables given in the command

### Execute JAR
Compile Gradle
~~~
    gradle build
~~~
Go to build/libs and execute
~~~
   java -Dserver.port=port_server -Dspring.redis.host=host_db -Dspring.redis.port=port_db -Dspring.redis.password=db_password -Dspring.redis.database=0 -jar Clean-Code.jar 
~~~

is required update all variables given in the command.

### Note:

For Test This application , we Use Redis DB provided By redis labs free account.

If you want test the application since code, before execure <b>Gradle build</b> please edit the application.properties
and put the database credential

<b>application.properties file</b>
~~~

spring.redis.host=db_hot
spring.redis.port=db_port
spring.redis.password=db_password
spring.redis.database=0

~~~

The application.properties file you can find into src/main/resources folder.

Next to edit the file, yo can run the application with


~~~
    gradle bootRun
~~~

