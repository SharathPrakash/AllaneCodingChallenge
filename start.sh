#!/bin/bash

# Start the MySQL Docker container
docker run -d -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=ALLANE -e MYSQL_USER=root -e MYSQL_PASSWORD=root mysql/mysql-server:latest

# Wait for the MySQL server to start
echo "Waiting for MySQL server to start..."
while ! docker exec -it mysql mysqladmin ping --silent; do
    sleep 10
done
echo "MySQL server started..."

# Create the database
docker exec -it mysql mysql -uroot -proot -e "CREATE DATABASE ALLANE;"

# Build the application (assuming you have Maven installed)
mvn clean install

# Start the application
mvn spring-boot:run
