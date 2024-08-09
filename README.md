<a href="https://git.io/typing-svg"><img src="https://readme-typing-svg.herokuapp.com?font=Fira+Code&weight=600&size=50&pause=1000&center=true&vCenter=true&color=purple&width=835&height=70&lines=POS+MANAGEMENT+SYSTEM" alt="pos" /></a>

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JavaEE](https://img.shields.io/badge/JavaEE-6DB33F?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/Apache%20Tomcat-F8DC75?style=for-the-badge&logo=apache-tomcat&logoColor=black)

## Overview
The POS System Backend is a RESTful API designed to support the operations of a Point of Sale system. This backend service manages customer data, item data, order processing, and inventory tracking, providing essential features like creating, reading, updating, and deleting records.

## Contents
- [Key Features](#key-features)
- [Technology Stack](#technology-stack)
- [Setup Instructions](#setup-instructions)
- [How to Use](#how-to-use)
- [Project Structure](#project-structure)
- [Dependencies](#dependencies)
- [API References](#api-references)
- [License](#license)
  
## Key Features
- Customer record management
- Order processing and inventory management
- Real-time item quantity updates
- Discount application and total calculations
- Secure endpoints with authentication

## Technology Stack
- **Backend Framework :** Java EE
- **Database :** MySQL
- **Build Tool :** Maven
- **Application Server :** Apache Tomcat 10
- **JDK :** OpenJDK 17

## Setup Instructions
### Prerequisites
- Java 17 (OpenJDK 17)
- Maven
- MySQL
- Apache Tomcat 10

### Installation Steps
1. **Clone the Repositories:**
   - Backend: 
     ```bash
     git clone https://github.com/charmiesw/AAD_Phase1_POS_BackEnd.git
     ```
   - Frontend:
     ```bash
     git clone https://github.com/charmiesw/AAD_Phase1_POS_FrontEnd.git
     ```

2. **Database Configuration:**
   - Create a MySQL database named `pos_aad`.
   - Update the `persistence.xml` file with your MySQL credentials:
     ```xml
     <properties>
         <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/pos_aad"/>
         <property name="javax.persistence.jdbc.user" value="your_username"/>
         <property name="javax.persistence.jdbc.password" value="your_password"/>
         <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
         <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect"/>
         <property name="hibernate.hbm2ddl.auto" value="update"/>
     </properties>
     ```

3. **Build the Project:**
   ```bash
   mvn clean install
   ```

4. **Deploy to Tomcat**
    - Ensure Tomcat is installed and running.
    - Copy the generated WAR file from the `target` directory to the Tomcat `webapps` directory.
    - Restart Tomcat.

## How To Use
### Running the Server
After installation, run the server by starting Tomcat.

## Project Structure

### Back-end

The back-end code is organized to follow best practices and maintainability. Important directories and files include:

- src/main/java: Directory containing Java classes.
- src/main/resources/schema: Database schema.
- src/main/resources/File: Tomcate Text File.
- src/main/webapp/WEB-INF/: Configuration files for the JavaEE application.

#### Project Packages

The back-end codebase is further organized into the following packages:

(src/main/java/lk.ijse.pos)

- **api** : Contains classes defining the API endpoints and services.
- **bo** : Business Objects - classes that encapsulate business logic.
- **dao** : Data Access Objects - classes responsible for database interactions.
- **entity** : Entity classes representing database tables.
- **dto** : Data Transfer Objects - classes used for data exchange between layers.
- **filter** : Contains classes implementing filters for intercepting and processing requests.

## Getting Started

To set up and run the Shop Management project locally, follow these steps:

&nbsp;1. Clone the repository.  
&nbsp;2. Set up the back-end dependencies.  
&nbsp;3. Configure the database connection.  
&nbsp;4. Deploy the JavaEE application on the Apache Tomcat server.


## Usage

Once the back-end application is running, users can seamlessly interact with the front-end without interruptions. The system consists of four main pages: Customer, Items, Place Order, and Order Details. Users can effortlessly navigate through these pages to perform various actions and tasks.

## Dependencies

#### Back-end

- Java EE : Enterprise Edition of the Java platform for building robust and scalable enterprise applications. 
- Apache Tomcat : Servlet container that implements the Java Servlet and JavaServer Pages technologies. (Version 10.1.24)

#### Database

- MySQL Connector : Java-based driver for connecting to MySQL databases. (Versoin 8.0.33)
- Java Naming and Directory Interface (JNDI): Java API for connecting to directory services, used for managing database connections efficiently through connection pooling, enhancing performance and scalability.

#### Development Tools

- Maven : Build automation and project management tool. (Version 4.0.0)

### Accessing the API
The API will be available at `http://localhost:8080/`

## API References
For detailed API documentation, please refer to the project’s Swagger UI available.

This documentation provides interactive access to the following APIs and Refer to the project Documentations :

- **Customer API documentation URL :**  [https://documenter.getpostman.com/view/35386294/2sA3s1pXiX]
- **Item API documentation URL :**  [https://documenter.getpostman.com/view/35386294/2sA3s3FqgC]
- **Order API documentation URL :**  [https://documenter.getpostman.com/view/35386294/2sA3s3FqgE]
- **Order Details API documentation URL :**  [https://documenter.getpostman.com/view/35386294/2sA3s3FqgG]

You can reffer to the json formats of the postman APIs as (src/main/resources/postman)

## License
This project is licensed under the MIT License. See the [LICENSE](https://github.com/charmiesw/AAD_Phase1_POS_BackEnd/blob/main/LICENSE) file for details.

<div align="center">

#### This project is licensed under the [MIT License](LICENSE)

#### © 2024 All Right Reserved, Designed By [Charmie Weerapperuma](https://github.com/charmiesw)

</div>

