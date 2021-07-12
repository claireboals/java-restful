# Code Challenge: Java RESTful Web Service
Java RESTful web service made using Spring Boot with data persistence using JPA and H2.


Build and run the project:
```
./mvnw spring-boot:run
```

All unit tests can be found src/test/java/challenge/javarestful

Run the tests:
```
./mvnw test
```

Endpoints:
```
GET /products - returns all products

GET /products/{id} - returns product with id if it exists

POST /products - create a new product
```
```
GET /albums - returns all albums

GET /albums/{id} - returns album with id if it exists

POST /albums - create a new album
```
```
GET /images - returns all images

GET /images/{id} - returns image with id if it exists

POST /images - create a new image
```

