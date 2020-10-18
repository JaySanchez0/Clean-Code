FROM openjdk:8
WORKDIR /rulette/app
COPY . /rulette/app
CMD ["java","-Dserver.port=${PORT}","-Dspring.redis.host=${REDISHOST}","-Dspring.redis.port=${REDISPORT}","-Dspring.redis.password=${REDISPASSWORD}","-Dspring.redis.database=0","-jar","build/libs/Clean-Code.jar"]
