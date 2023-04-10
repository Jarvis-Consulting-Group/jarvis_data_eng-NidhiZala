# Linux Cluster Monitoring Agent

The development of a Linux Cluster Monitoring agent enables individuals to monitor hardware specifications and system usage information on multifarious Linux servers. The agent uses Bash scripts to gather machine hardware data, which is then saved in a PostgreSQL database that has been provisioned by Docker. Details on resource utilisation are obtained by default using Crontab and can then be examined using SQL statements. Each evaluation took place on a Google Cloud Platform virtual desktop hosting the CentOS7 Linux system. Each script had been carefully examined to ensure it adhered to all specifications.Major Technologies utilized for the project are bash scripts, psql, git, postgresql database, Intellij Idea, Docker and lastly Jarvis remove desktop setup provisioning linux on VNC viewer using Google cloub platform.

# Quick Start 

- Start a psql instance using psql_docker.sh
```bash
psql_docker.sh start

```
- Create tables using ddl.sql
```bash
psql -h localhost -U postgres -d host_agent -f sql/ddl.sql
```
- Insert hardware specs data into the DB using host_info.sh
```bash
bash host_info.sh psql_host psql_port db_name psql_user psql_password
```

- Insert hardware usage data into the DB using host_usage.sh
```bash
bash host_usage.sh psql_host psql_port db_name psql_user psql_password
```
- Crontab setup
```
* * * * * bash <your path>/linux_sql/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log
```
# Implementation

## Architecture


## Scripts
Below descriptions elaborates all the scripts utilized in the project.
## psql_docker.sh
### Description
This script is used to create | start | stop the docker container for the project running on the postgres database based condition is the container is working or not. 
### Usage
Below scripts will show how can we start stop or create docker container. psql_username = username psql_password = password for the following scripts as specified for the docker container.

Start postgres container script: 
```
psql_docker.sh start
```
Stop Postgres container script:
```
psql_docker.sh stop
```
Create Portgres container Script:
```
psql_docker.sh create psql_username psql_password
```

## host_info.sh
### Description
This script will gather all server/node host hardware details and transmit them to a Postgres database. This script gathers information on the hostname, CPU, and RAM.
### Usage
The gathered hardware specifications data will be inserted into the Postgres database using this script. the parameter for the script are as follows
psql_host: Hostname on which the database is running usually localhost 
psql_port : Port Number on which our docker runs which is 5432
db_name : The database name, in this case host_agent
psql_user : username for Postgres (set to username in this case)
psql_password : Postgres Password (set to password in this case)

```bash
bash host_info.sh psql_host psql_port db_name psql_user psql_password
```
## host_usage.sh
### Description
Using Crontab this script runs every minute in the system gathering the usage information of the system and inserting into the usage database. 
### Usage
To run the bash script file and insert the data into the database the below command is used. The parameters/ arguments passed are same as host_info for instance psql_host, psql_port, db_name psql_user psql_password.
```
bash host_usage.sh psql_host psql_port db_name psql_user psql_password
```
## crontab
### Description
Most useful functionality for the project is Crontab which enable the usage script to be ran every minute and gathering usage info and inserting it at the same time to the database.

### Usage
The below command allows to edit the crontab file:
```
crontab -e
```
Below code displays how crontab can be used to run the script every minute for the project.

```
* * * * * bash <file path>/host_usage.sh psql_host psql_port db_name psql_user psql_password > /tmp/host_usage.log
```
Use the below code to check the crontab file 

```
crontab -l
```
## queries.sql


## Database Modeling
PostGres contain the database named host_agent. The Database contains 2 tables host_info and host_usage for storing the script values received.
## host_info

Column | Type | Description
--------------|------|--------------
id | `SERIAL` | This is the primary key column, contains the unique identification (id) when the data is inserted into the database.
hostname | `VARCHAR` | This variable stores the hostname for Linux server used.
cpu_number | `INTEGER` | This variable stores the number of CPU's inside the server.
cpu_architecture | `VARCHAR` | This variable stores the CPU Server Architecture.
cpu_model | `VARCHAR` | This variable stores the Server's CPU model.
cpu_mhz | `FLOAT` | This variable stores the Clock speed of CPU server.
L2_cache | `INTEGER` | This variable stores the Size for L2 Cache of CPU.
total_mem | `INTEGER` | This variable stores the memory space on the server.
timestamp | `TIMESTAMP` | This variable stores the timestamp when the data is added to the database. By default the server's current timestamp is used.


## host_usage

Column | Type | Description
--------------|------|--------------
timestamp | `TIMESTAMP` | Indicates the timestamp/ time when the data is added to the database
host_id | `SERIAL` | Contains the host id from which the database is gathered. Foreign key from the host info table.
memory_free | `INTEGER` | Free memory left inside the storage.
cpu_idle | `DECIMAL` | Percentage of CPU that is idle currently
cpu_kernel | ` DECIMAL` | Proportion of CPU utilized currently.
disk_io | `INTEGER` | Recent Quantity of disc I/O is allocated to this variable.
disk_available | `INTEGER` | Available storage space inside the root directory of server.

# Test

The Bash Scripts have been created on Jarvis Remote Desktop provisioned from Google Cloud Platform(GCP). The whole module could be designed and tested on a single server. The project contains PostGres database using Docker Image. The Tables are created using SQL queries and insert operations to the database are performed using queries. For instance, in the initial stages, a test database has been manually entered and later the scripts are run to test the working of scripts.
# Deployment

For this project, the source code is managed through GitHub. By executing the host usage.sh script once per minute to get information on host resource utilisation, Crontab is utilised to insert entries into the host usage table.
The Postgres 9.6-Alpine image generates a database container that is loaded in Docker.

# Improvements
- GUI can be developed for the application to make it more user firendly.
- The data stored is much indefinite format, for future scope a module can be added where we delete old data or archive it.
