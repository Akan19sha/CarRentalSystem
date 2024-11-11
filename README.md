# Car Rental System

The Car Rental System is a backend web application developed to automate car rental operations, offering a streamlined solution for managing car inventory, processing reservations, handling billing, and generating reports. This application is built to provide efficiency and ease of use for both rental agencies and customers.

## Features
- **Car Inventory Management**: Track available and rented cars.
- **Reservation System**: Manage reservations with booking and canceling functionalities.
- **Billing and Payment Processing**: Process payments and generate bills for reservations.
- **Logging and Error Handling**: Track key actions and errors for debugging.
- **Unit and Integration Testing**: Ensures reliability with JUnit and Mockito.



## Getting Started

### Prerequisites
To run this project, ensure you have the following installed:
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/)
- [MySQL](https://dev.mysql.com/downloads/)
- [Eclipse IDE](https://www.eclipse.org/downloads/)

## Run in your System
1. **Open Project in Eclipse**
2. **Create the Database**:
   - Open MySQL and create a new database:
   ```sql
   CREATE DATABASE yourdatabasename;
3. **Configure the Database**:
   - Open the `application.properties` file located at `src/main/resources/application.properties`.
   - Update the file with your database credentials by modifying the following properties:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabasename
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
4. **Build the Project**:
   - Use Maven to clean and build the project. Open your terminal and run the following command:
   ```bash
   mvn clean install
5. **Run the Project**:
   - Right-click on the main class: `Run As > Spring Boot App`
## Database
The Car Rental System uses a relational database to store and manage data related to cars, users (admin and customers), reservations, and billing. The schema includes key tables such as:

- **`Car`**: Stores information about the cars available for rental.
- **`Reservation`**: Tracks customer reservations.
- **`Billing`**: Stores billing and payment information related to reservations.



### Clone the Repository
Clone the project to your local machine:
```shell
 https://github.com/Akan19sha/CarRentalSystem.git
```

