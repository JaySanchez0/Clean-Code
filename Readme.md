 # Clean Code - Masiv
 
 #### Present By:
 
 Jeisson G. Sanchez R. 
 
 
 ### Test
 
 [![CircleCI](https://circleci.com/gh/JaySanchez0/Clean-Code.svg?style=svg)](https://circleci.com/gh/JaySanchez0/Clean-Code)
 


### Docker Hub

[ver](https://hub.docker.com/r/jsanchez0/roulette-java)

### Docker build

~~~
    docker build --tag name_image .
~~~

### Execute the image

~~~
    docker run -e PORT=container_port -e REDISHOST=url_redis -e REDISPORT=port_redis -e REDISPASSWORD=redis_password -p machine_port:container_port jsanchez0/roulette-java
~~~

### Execute JAR

~~~
   java -Dserver.port=port_server -Dspring.redis.host=host_db -Dspring.redis.port=port_db -Dspring.redis.password=db_password -Dspring.redis.database=0 -jar Clean-Code.jar 
~~~