# POS SYSTEM BACKEND

## Overview
The POS System Backend is a comprehensive RESTful API built to efficiently support the various functionalities of a Point of Sale (POS) system. This backend service plays a crucial role in handling diverse aspects of the POS system, including:

- **Customer Data Management**: It allows for the creation, retrieval, updating, and deletion of customer profiles and information, ensuring that all customer interactions and transactions are accurately recorded and managed.

- **Item Data Management**: This feature includes managing product details, such as item codes, names, quantities, and prices. The backend supports operations for adding new items, updating existing item details, and removing items from the system.

- **Order Processing**: The API facilitates the entire order lifecycle from initiation to completion. This includes processing sales transactions, tracking order statuses, and managing payment information.

Overall, the POS System Backend ensures seamless integration and operation of the POS system, enabling businesses to manage their sales, inventory, and customer interactions effectively through a set of robust API endpoints.

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
- **Customer Record Management**: Efficiently handles the creation, retrieval, updating, and deletion of customer profiles. This feature ensures that customer information is accurately maintained and easily accessible for personalized service and transaction history.

- **Order Processing and Inventory Management**: Manages the complete order lifecycle, from order initiation to completion. It tracks sales transactions, updates inventory levels in real-time, and supports inventory management tasks to ensure optimal stock levels.

- **Real-Time Item Quantity Updates**: Provides immediate updates on item quantities as sales occur or inventory is adjusted. This feature helps in maintaining accurate stock counts and prevents discrepancies between physical and system inventory.

- **Discount Application and Total Calculations**: Allows for the application of discounts to orders and performs precise calculations of totals, including tax and final amount. This ensures that pricing adjustments are accurately reflected in transaction totals.

- **Secure Endpoints with Authentication**: Ensures that all API endpoints are protected by robust authentication mechanisms. This feature safeguards sensitive data and system operations by allowing access only to authorized users, thereby enhancing overall system security.

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

#### © 2024 All Right Reserved, Designed By [Charmie Weerapperuma](https://github.com/charmiesw)

</div>

