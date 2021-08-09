To build, deploy & run:
```
./mvnw clean package
cp .\target\sensor-metrics-*.jar src/main/docker
cd src/main/docker
docker-compose down
docker rmi sensor-metrics:latest
docker-compose config
docker-compose up --build
```

Testing performed 
- unit tests for query creation
- manually via POSTMAN against deployed application
  - Postman collection: Sensor-metrics.postman_collection.json
  - valid (not enforced or error handled) metric types: temperature, humidity, windspeed
  - valid (not enforced or error handled) aggregation types: min, max, avg

Sample queries
- Get all sensors
  - ```curl --location --request GET 'http://localhost:8080/sensor'```
- Get all readings
  - ```curl --location --request GET 'http://localhost:8080/reading'```
- Create sensor
  - ```curl --location --request POST 'http://localhost:8080/sensor' \--header 'Content-Type: application/json' \--data-raw '{"sensorId": 100,"metadata": "{\"Location\":\"Galway\"}"}'```
- Create reading
  - ```curl --location --request POST 'http://localhost:8080/reading?sensorid=100&temperature=1.0&humidity=1.0&windspeed=1.0&timestamp=1628522480303' \--header 'Content-Type: application/json'```
- Get average temperature, windspeed and humidity for the last 10 days for sensor 100
  - ```curl --location --request GET 'http://localhost:8080/reading/aggregate?sensors=100&metrics=temperature, windspeed, humidity&aggregationtype=avg&days=10' \--header 'Content-Type: application/json'```
- Get average temperature and humidity for the last 10 days for all senesors
  - ```curl --location --request GET 'http://localhost:8080/reading/aggregate?metrics=temperature, humidity&aggregationtype=avg&days=10' \--header 'Content-Type: application/json' \--data-raw '{"metadata": "{\"Location\":\"Galway\"}"}'```
- Get latest average windspeed for sensor 100 (interpreted "latest" as 1 day)
  - ```curl --location --request GET 'http://localhost:8080/reading/aggregate?sensors=100&metrics=temperature, windspeed, humidity&aggregationtype=avg' \--header 'Content-Type: application/json' \--data-raw '{"metadata": "{\"Location\":\"Galway\"}"}'```

