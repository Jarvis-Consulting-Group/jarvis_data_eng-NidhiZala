# Introduction
The Grep app works in a way where it looks for patterns in all of the files for the specified directory and outputs/stores the results to another output file. The project has been implemented using the programming approach where we are using standard methods like looks and recursion based ideas, an improvised version for this project used Java 8 lambda interfaces. The application was developed using the Java programming language, with Maven as the build tool. It utilizes Streams, Lambda, and RegEx pattern matching in its implementation. The application can be executed either from the command line, provided that the JRE (Java Runtime Environment) is installed on the system, or from within a Docker container.

# Quick Start
To access the application and run it on any system, two methods can be used:
 - ## Using Dockerhub
   - ### Pulling docker image from docker hub 
   ```bash
   docker pull zala2609/grep
   ```
   - ### Run the docker container 
   ```bash
   export docker_user = zala2609
   docker run --rm \ -v `pwd`/data:/data -v `pwd`/log:/log \ ${docker_user}/grep .*Romeo.*Juliet.* /data /log/grep.out
   ```
 - ## Using Github
    - Clone the github repo from the profile for grep app
    - Edit program configurations, pass the parameters correctly in order " pattern source_directory ouput_directory "
    - Build mvn project (also check the pom file if any changes required"
      ```bash
      mvn clean compile package
      ```
    -  Run the main class of JavaGrepImp
 
# Implemenation
## Pseudocode
```bash 
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
```

## Performance Issue
The current implementation of the application stores files from a directory in an ArrayList, which grows dynamically. However, the ArrayList always grows by half in size, even if only one element is added, which can lead to unnecessary memory allocation. To solve this problem, we can use a buffer stream or Stream API, both of which allocate memory only when needed. A buffer stream reads or writes data in chunks, avoiding the allocation of more memory than necessary. The Stream API allows us to perform operations on a collection of data without storing it in memory, making it more memory-efficient than an ArrayList. By using these approaches, we can reduce the overall memory footprint of the application.

# Test
During the testing phase of the application, I utilized two different tools to ensure that the code is functioning correctly. Firstly, I incorporated the SLF4J logger, which displays the output of each module when performing unit tests. This helped me to track down any issues that arose and verify that the output was correct. Additionally, I used sample data input to validate the results and ensure that the program was working as intended. Secondly, I utilized a debugger during integration testing, which helped me to find and correct any errors that occurred during this phase. With these tools, I was able to thoroughly test the application and make sure that it was functioning correctly.

# Deployment
I have dockerized the app for the propose of depolyment, the below steps where used to dockerise the application:
  - Create an empty docker file and write the following program for the same :
    ```bash
    FROM openjdk:8-alpine
    COPY target/grep*.jar /usr/local/app/grep/lib/grep.jar
    ENTRYPOINT ["java","-jar","/usr/local/app/grep/lib/grep.jar"]
    ```
  - Build the maven package 
    ```bash
    mvn clean package
    ```
  - Login to docker or create dockerhub account before moving ahead:
    ```
    docker login
    ```
    Enter your id and password after the above statement
  - Build the image :
    ```bash
    export docker_user  = zala2609 
    docker build -t ${docker_user}/grep
    ```
  - Check is the image is created :
    ```bash
    docker image ls | grep "grep"
  - Run the docker container 
    ```bash
    docker run --rm \ -v `pwd`/data:/data -v `pwd`/log:/log \${docker_user}/grep .*Romeo.*Juliet.* /data /log/grep.out
    ```
  - Push the container to docker hub
    ```bash
    docker push ${docker_user}/grep
   
    ```
  - vist : hub.docker.com check for the image you pushed
# Improvement
Some major improvements in the project are as follows:
- We can use STREAM API for improving the memory efficiency for instance using java.util.Scanner for the same.
- We can add line number in the ouput file stating the positiong where is the output derived from
- For testing pupose Junit classes can be used.
