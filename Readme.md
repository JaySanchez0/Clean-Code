 # Clean Code - Masiv
 
 #### Present By:
 
 Jeisson G. Sanchez R. 
 
 
 
 ### Nota:
 
By run and test the app, please configure the next variable in file application.properties into resources folder
 
~~~
spring.redis.host=db_host
spring.redis.port=db_port
spring.redis.password=db_password
spring.redis.database=0

~~~

### Docker Hub

[ver](https://hub.docker.com/repository/docker/jsanchez0/roulette-java)

### Docker build

~~~
    docker build -tag name .
~~~

### Execute the image

~~~
    docker run -p machine_port:container_port -e PORT=container_port image_name
~~~

### Execute JAR

~~~
   java -Dserver.port=port_server -Dspring.redis.host=host_db -Dspring.redis.port=port_db -Dspring.redis.password=db_password -Dspring.redis.database=0 -jar Clean-Code.jar 
~~~