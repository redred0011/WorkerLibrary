# WorkerLibrary

To run the project, you need:

- **Java 17** (or higher).
- **Maven** (if you don't use `mvnw').
- **Docker** and **Docker Compose**.

## Installation and startup steps

### 1. **Clone the repository**.

Clone the repository to your computer:

``bash
git clone **https://github.com/redred0011/WorkerLibrary.git**

Then go to the project directory:
cd WorkerLibrary

### 2. **Build the project using Maven**

If you are using locally installed Maven, run the following command in the root directory of your project:

bash
Copy the code
mvn clean package
If you have a problem with Maven, you can also use the mvnw script provided in the project:

bash
Copy the code
./mvnw clean package
This command will build the application and generate a JAR file in the target directory.

### 3. **Launch the application using Docker Compose**

Once the compilation is complete, launch the application using Docker Compose:

bash
Copy the code
docker-compose up --build
This command will launch both the Spring Boot application and the MySQL database in Docker containers.

### 4. **Accessing the application**

The application will be available at:

arduino
Copy the code
http://localhost:8080
The MySQL database will be accessed on port 3306, and the database login credentials are configured in the docker-compose.yml file:

User: root
Password: bartek
Database name: sys

**WorkerLibrary** is a Java-based application that provides a REST API for managing employee data within a database, built with Spring Boot, Docker, MySQL, and JDBC. The application allows you to retrieve, add, update, and delete employee records, as well as search for employees based on various criteria.

## WorkerRepository Class

The `WorkerRepository` class is responsible for accessing and managing employee data in the database. It offers the following methods:

- **`getAll()`**: Returns a list of all employees in the database.
- **`getById(int id)`**: Returns an employee based on the provided ID.
- **`save(List<Worker> workers)`**: Saves a list of employees to the database.
- **`update(Worker worker)`**: Updates an employee's data in the database.
- **`delete(int id)`**: Deletes an employee based on the provided ID.
- **`searchSalary(double searchSalary)`**: Returns employees who earn a salary higher than the specified amount.
- **`searchPosition(String position)`**: Returns employees holding the specified position.

## Worker Class

The `Worker` class represents an employee entity. It contains fields such as ID, first name, last name, position, and salary. The class includes constructors and methods for validating employee data.

## LibraryController Class

The `LibraryController` class is a REST API controller that handles operations related to employees. It provides the following endpoints:

- **`GET /library`**: Returns a list of all employees.
- **`GET /library/{id}`**: Returns an employee based on the provided ID.
- **`POST /library`**: Adds a list of employees to the database.
- **`PUT /library/{id}`**: Updates an employee's data based on the provided ID.
- **`PATCH /library/{id}`**: Partially updates an employee's data based on the provided ID.
- **`DELETE /library/{id}`**: Deletes an employee based on the provided ID.
- **`POST /library/salary/{salary}`**: Searches for employees who earn more than the specified salary.
- **`POST /library/position/{position}`**: Searches for employees holding the specified position.

## Tools Used for Development

- **IntelliJ IDEA**
- **Docker Desktop**
- **Docker Compose**
- **Postman**
- **Maven**
- **Spring Boot**
- **Liquibase**
- **MySQL**
- **H2 Database**
- **MapStruct**
- **Lombok**
- **Spring Security**
- **JUnit**
- **Spring Boot Test**

This application simplifies the management of employee data within a database. For any issues or questions, please contact the author.

---

**Author**: [redred0011](https://github.com/redred0011)
