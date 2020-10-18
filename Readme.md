 # Clean Code - Masiv
 
 #### Presentado por:
 
 Jeisson G. Sanchez R. 
 

### Docker Hub

[ver](https://hub.docker.com/repository/docker/jsanchez0/roulette-java)

### Docker build

~~~
    docker build -tag name .
~~~

### Ejecutar la imagen

~~~
    docker run -p machine_port:container_port -e PORT=container_port image_name
~~~

### Execute JAR

~~~
    java -Dserver.port=port_server -Dspring.redis.host=host_db -Dspring.redis.port=port_db -Dspring.redis.password=0 -Dspring.redis.database=0 -jar Code-Clean.jar 
~~~
