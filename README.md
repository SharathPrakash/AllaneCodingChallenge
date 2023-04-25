# Leasing Contract Application

This Java class represents a RESTful web service for managing leasing contracts. It provides endpoints for creating,
retrieving and updating customers, vehicles and leasing contracts.

The service is implemented using the Spring Boot framework, and depends on the following services and entities:
LeasingContractService, CustomerService, VehicleService, Customer entity, Vehicle entity, LeasingContract entity,
ContractOverview model and ContractRequest model.

## Endpoints

The following endpoints are available:

- GET /customers/{id} - Retrieve a customer by ID
- GET /customers - Retrieve all customers
- POST /customers - Create or update a customer
- GET /vehicles/{id} - Retrieve a vehicle by ID
- POST /vehicles - Create or update a vehicle
- POST /contracts - Create a new leasing contract
- GET /contracts - Retrieve an overview of all leasing contracts

## Preconditions

To run the application, you will need:

- Java Development Kit (JDK) version 8 or above
- Apache Maven build tool version 3.3 or above

## Starting the Application

To start the application, navigate to the root directory of the project and run the following command:

```
./start.sh
```

which will start the MySQL as Docker and along with that spring boot application will also be running.
This will start the application on port 8080 by default. You can access the endpoints by making HTTP requests
to http://localhost:8080/swagger-ui.html

## Reason for Chosen Solution

Spring Boot is a popular framework for building web applications in Java. It provides a lot of built-in functionality,
such as automatic configuration and dependency injection, which makes it easy to get started with a new project.

In this project, we have used Spring Boot to build a REST API for managing leasing contracts between customers and
vehicles. The API is designed to be simple and easy to use, with endpoints for creating, updating, and retrieving
leasing contracts.

We have also used the Spring Data JPA module to interact with a database, which makes it easy to perform CRUD operations
on entities. This module also provides support for pagination, sorting, and other common database operations, which
makes it a good choice for building a data-driven application like this one.

## How to test

After accessing the swagger URL

- Create the Vehicles using '/vehicles' - POST request
- Create the Customer using '/customers' - POST request
- Link the contract using '/contracts' - POST request

#### For testing already added with few test data.