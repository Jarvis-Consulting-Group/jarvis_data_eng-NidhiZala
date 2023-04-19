# Introduction
This software showcases the implementation of JDBC for establishing a linkage between Java programs and RDBMS. The project entails the formulation of Data Access Objects Patterns to execute CRUD (Create, Read, Update, and Delete) actions. The dependencies were defined by utilizing Apache Maven. 
While developing this program, various tools were utilized :
* The PostgreSQL database management system.
* Dbeaver tool - For producing database scripts
* Docker container - For establishing a PSQL instance
* IntelliJ IDE - For programming the project.

# Implementation
1. Ckeck Docker status and start Docker container<br>
```
sudo systemctl status docker
docker ps
docker container start jrvs-sql
```
2. Connect to the PSQL instance change port if using different port <br>
```
psql -h localhost -U postgres -p 5554
```
3. Create database hplussport and load all the data in the tables by running these sql files <br>
```
psql -h localhost -U postgres -p 5554 -f database.sql
psql -h localhost -U postgres -d hplussport -p 5554 -f customer.sql
psql -h localhost -U postgres -d hplussport  -p 5554 -f product.sql
psql -h localhost -U postgres -d hplussport -p 5554 -f salesperson.sql
psql -h localhost -U postgres -d hplussport -p 5554 -f orders.sql
```
## ER Diagram
![Entity Relationship Diagram for database hplussport](/home/centos/dev/jarvis_data_eng_NidhiZala/core_java/ERD.png)

## Design Patterns
* **The Data Access Object (DAO) pattern** : This pattern is a software design approach that helps to separate the application layer from the persistence layer, usually a relational database. By providing a simplified API, the DAO pattern shields the application from the intricacies of performing CRUD operations on the underlying storage mechanism. This approach allows both layers to evolve independently, without any knowledge of each other. In this project, DAO classes were used to implement and retrieve data from the database, which was then utilized by the customer and order classes.
* **The Repository pattern**: The Repository pattern is another design approach that simplifies database-related operations such as adding, updating, deleting, and selecting items from a collection. The Repository pattern provides straightforward methods to perform these operations without requiring knowledge of database-specific details like connections and commands. By separating the data access layer from the business logic layer, the Repository pattern makes the code easier to maintain and modify.
# Test
The testing starts from base level till various levels of development.
1. To ensure docker is running:<br>
   ```
   sudo systemctl status docker 
   sudo systemctl start docker
   docker ps -f name=jrvs-sql
   ```
2. To check if the database has been connected <br>
   ```psql -h localhost -U postgres -p 5554 -d hplussport```
3. Firing SQL queries to check if the data is correctly populated into the databased and the changes are updated or not <br>
   ```SELECT COUNT(*) FROM customer;```
4. Generate ERD diagram in Dbeaver for the tables in database hplussport.
5. Run the java file for checking the JDBC connection and pom.xml file for check maven working correctly <br>
5. Run and debug the java file after writing code for each CRUD operations.

