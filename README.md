E-Commerce Product & Order Management System
This project contains two microservices: ProductService and OrderService, which form a simple E-commerce Product and Order Management System. These microservices interact with each other and manage data with PostgreSQL and cache product data with Redis.
Features
Two microservices: ProductService (to manage products) and OrderService (to manage orders)
PostgreSQL for data persistence
Redis for caching product details
REST API communication between microservices
Custom exception handling
Request validation
Connection pooling with HikariCP
Java 8+ features (Streams, Lambdas, etc.)
Dockerized services with Docker Compose

How to Run the Application
First, clone this repository to your local machine and navigate to the project folder:
git clone [https://github.com/your-repository/ecommerce-system.git](https://github.com/kailay22/E-commerce1.git)
cd ecommerce-system

1. Ensure that you have the latest Maven installed. Then, navigate into each service's directory (both product-service and order-service) and build the services using the following command:
mvn clean package
This command will create the JAR files for both services inside the target directory of each microservice.

2. Running with Docker Compose
   Once the services are built, run the following command to build and start all services along with PostgreSQL and Redis containers:
   docker-compose up --build

This command will:

Spin up a PostgreSQL database on port 5432
Spin up a Redis cache on port 6379
Spin up ProductService on port 8080
Spin up OrderService on port 8181
The application will be running with the following endpoints:

ProductService: http://localhost:8080
OrderService: http://localhost:8181

3. Testing the Application
   You can use Postman or curl to test the APIs:

ProductService API:

*Get all products:
GET /products
curl -X GET http://localhost:8081/products

*Add a new product:
POST /products

curl -X POST http://localhost:8081/products \
-H "Content-Type: application/json" \
-d '{
"name": "Laptop",
"price": 9500,
"description": "High-end laptop"
}'

*Get product by ID:
GET /products/{id}

curl -X GET http://localhost:8081/products/1

OrderService API:

*Create an order:
POST /orders

curl -X POST http://localhost:8082/orders \
-H "Content-Type: application/json" \
-d '{
"productId": 1,
"quantity": 2
}'

*Get all orders:
GET /orders

curl -X GET http://localhost:8082/orders

4. Stopping the Services

To stop all running services, use the following command:

docker-compose down

Configuration

Environment Variables
The following environment variables are used in the docker-compose.yml file for configuring the services:

PostgreSQL Configuration:

POSTGRES_DB: jdbc:postgresql://localhost:5432/postgres
POSTGRES_USER: postgres
POSTGRES_PASSWORD: 1234
Redis Configuration:

Redis is running on redis container (localhost:6379).
ProductService:

Spring profiles (SPRING_PROFILES_ACTIVE) are set to prod.
Database URL, username, and password are configured using environment variables (SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME, SPRING_DATASOURCE_PASSWORD).
OrderService:

Feign client in OrderService communicates with ProductService at http://product-service:8080.
