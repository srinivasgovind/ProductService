# ProductService
APIs Implementation for Product Service

## Steps to Install this Project Locally

1. Clone the project to your local machine.
2. Set up your PostgreSQL and replace the values in `application.properties`.
3. Set up your Elasticsearch and replace the values accordingly.
4. Run `mvn clean install`.
5. Ensure the UserService is running on a different port to validate the token.
6. You should now be able to run this project successfully.

## What is this Project?

1. Product Service is one of the microservices used in building an e-commerce web application.
2. The Adapter Design Pattern is used to implement both Product CRUD operations through the PostgreSQL database and the FakestoreAPI Client (3rd party).
3. It communicates with the "UserService", another microservice, to validate the provided token.
4. Integrated Redis to reduce API response time; if there is a cache miss, it stores the API response in the cache.
5. Integrated Elasticsearch for faster retrievals.
6. Implemented "Pagination & Sorting" for products.
7. Tables are created using "Flyway".