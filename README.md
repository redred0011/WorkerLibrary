# WorkerLibrary

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
- **MySQL Workbench**
- **Postman**

This application simplifies the management of employee data within a database. For any issues or questions, please contact the author.

---

**Author**: [redred0011](https://github.com/redred0011)
