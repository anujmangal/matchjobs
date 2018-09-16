# Job Matching API
Matching workers with appropriate jobs

# Requires
Java 1.8, Maven

# Building Project
```
mvn clean install
```

# Running Project (embedded Tomcat, default port 8080)
java -jar target\match-jobs-1.0-SNAPSHOT.jar

# API Docs
### GET /jobs/search/{workerId}
This REST API will return a list of matching jobs for the given worker

# Testing
```
mvn test
```
