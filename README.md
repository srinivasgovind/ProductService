# ProductService
APIs Implementation for Product Service

## Steps to Install this Project Locally

1. **Clone the Project**:
    - Clone the project to your local machine using the following command:
      ```bash
      git clone https://github.com/yourusername/ProductService.git
      cd ProductService
      ```

2. **Set Up PostgreSQL**:
    - Ensure you have PostgreSQL installed and running.
    - Update the database configurations in the `application.properties` file with your PostgreSQL details:
      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/yourdatabase
      spring.datasource.username=yourusername
      spring.datasource.password=yourpassword
      ```

3. **Set Up Elasticsearch**:
    - Ensure you have Elasticsearch installed and running.
    - Update the Elasticsearch configurations in the `application.properties` file:
      ```properties
      spring.elasticsearch.rest.uris=http://localhost:9200
      ```

4. **Build the Project**:
    - Run the following Maven command to clean and build the project:
      ```bash
      mvn clean install
      ```

5. **Run UserService**:
    - Ensure the UserService microservice is running on a different port to validate the token. Update the configuration if necessary to point to the correct UserService URL.

6. **Run the Project**:
    - You should now be able to run the ProductService project successfully using the following command:
      ```bash
      mvn spring-boot:run
      ```

## What is this Project?

1. **Microservice for E-commerce**:
    - Product Service is one of the microservices used in building an e-commerce web application.

2. **Adapter Design Pattern**:
    - The Adapter Design Pattern is used to implement both Product CRUD operations through the PostgreSQL database and the FakestoreAPI Client (3rd party).

3. **Token Validation**:
    - Communicates with the "UserService" microservice to validate the provided token.

4. **Redis Integration**:
    - Integrated Redis to reduce API response time. If there is a cache miss, it stores the API response in the cache.

5. **Elasticsearch Integration**:
    - Integrated Elasticsearch for faster retrievals.

6. **Pagination & Sorting**:
    - Implemented "Pagination & Sorting" for products to enhance data management and user experience.

7. **Flyway for Database Migrations**:
    - Tables are created and managed using Flyway for database migrations.

## Technologies Used

- **Java 17**
- **Spring Boot 3.2.2**
- **Spring Data JPA**
- **Spring Data Elasticsearch**
- **PostgreSQL**
- **Redis**
- **JWT for Authentication**
- **Maven for Build and Dependency Management**
- **Flyway for Database Migrations**

## Endpoints

### Product Endpoints

- **GET /products**: Retrieve all products.
- **GET /products/{id}**: Retrieve a product by its ID.
- **POST /products**: Create a new product.
- **DELETE /products/{id}**: Delete a product by its ID.
- **PUT /products/{id}**: Update a product by its ID.
- **GET /products/title/{title}**: Find a product by its title.

---