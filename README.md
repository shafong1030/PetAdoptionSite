# Pet Adoption Site
=====================

##Description
---------------
This is a full stack project, which aims at creating a website platform for users who want to adopt pet,
and the users who want to share the information of the animals they open for adoption.
Users can register a new account for log-in and log-out, they can change their password as well.
Registered users can upload the animals' information for other adoption.
Registered users can browse and search for animals that are up for adoption.
Users can communicate with each other by web socket messages to discuss the details for adoption. (Web socket message enhancement is still under development)

###Construction
---------------
-Databases: MySQL and MongoDB are used respectively for column-based entities and noSQL documents(e.g.photos)
-Back-end: java programming language is used in class creation, function implementation and CURD
-Front-end: HTML, CSS, javascript with bootstrap are used for website viewing

###Dependencies
---------------
**This project depends on the following libraries:**

* **Spring Boot**
+'spring-boot-starter'
+'spring-boot-starter-data-jpa'
+'spring-boot-starter-security'
+'spring-boot-starter-thymeleaf'
+'spring-boot-starter-web'
+'spring-boot-devtools (runtime)'
+'spring-boot-starter-data-mongodb'
+'spring-boot-starter-test (test)'
+'spring-boot-starter-websocket'
* **Thymeleaf**
+'thymeleaf-extras-springsecurity6'
* **Security**
+'spring-security-test (test)'
+'spring-security-messaging'
* **Database**
+'mysql-connector-j (runtime)'
+'spring-boot-starter-data-mongodb (for MongoDB connectivity)'
* **Lombok**
+'lombok (optional)'
* **Jackson**
+'jackson-core (version 2.17.1)'
+'jackson-databind (version 2.17.1)'
+'jackson-datatype-jsr310'
* **WebSockets**
+'spring-websocket'

###Installing
--------------
**To install and run this project, you'll need to have the following installed on your system:**
*Java 11 or later
*Maven 3.6 or later (or Gradle 6.3 or later)
*A MongoDB instance (local or remote)
*A MySQL instance (local or remote)

###Executing program
--------------
**To run the application, navigate to the project root directory and run the following command:**
mvn spring-boot:run
**This will start the Spring Boot application, and you can access the website by navigating to http://localhost:8080 in your web browser.**

###Version History
-------------------
-Initial Release

