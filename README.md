# MongDB Java Driver based wrappers mapping POJO
The main Gradle built project contains 3 simple sub projects, integrating POJO mapping: <br>
#### <a href = "https://projects.spring.io/spring-data-mongodb/">with Spring-Data-MongoDB, a part of the Spring-Data umbrella project</a>
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-mongodb <br>
compile group: 'org.springframework.boot', name: 'spring-boot-starter-data-mongodb', version: '2.0.0.RELEASE'

#### <a href = "https://github.com/mongodb/mongo-java-driver">without any mapping, just using a new version of basic Mongo-Java-Driver </a>
// https://mvnrepository.com/artifact/org.mongodb/mongo-java-driver <br>
compile group: 'org.mongodb', name: 'mongo-java-driver', version: '3.6.3'

#### <a href = "https://mongodb.github.io/morphia/">using MongDB Morphia POJO mapper</a>
// https://mvnrepository.com/artifact/org.mongodb.morphia/morphia <br>
compile group: 'org.mongodb.morphia', name: 'morphia', version: '1.3.2'
<br><br><br>
* Lombok used in this project requires Annotation processing (Intellij IDEA: File-Settings-Build,Execution,Deployment) to be enabled.
* Before you run any application, download and unzip zips.zip file, located in the root of the project.<br>
From the directory, where you've stored unzipped zips.json, run in your command line:<br>
```mongoimport --drop -d drivers -c zips zips.json``` <br>
This will create MongoDB database 'drivers' with 'zips' collection, which contains US zip codes. There are 29353 JSON objects in the database.
* Running MongoDB (port=27017) is also required.

Requesting Morphia Application in your browser ```http://localhost:8083/zips/hampton``` returns 38 entries for cities, which have 'hampton' in ther names, like this one:<br>
``` 
    {
        "id": "71744",
        "city": "HAMPTON",
        "location": [
            -92.52951,
            33.537613
        ],
        "population": 3505,
        "state": "AR"
    }
```
<br>
The same result will be obtained when changing port numbers:

* ```http://localhost:8081/zips/hampton``` for Spring Data Realization;
* ```http://localhost:8082/zips/hampton``` for the clean MongoDB Java Driver querying.

Calling ```http://localhost:8083/zips/``` (as well as for the ports 8081 and 8082) will return all the documents. While it takes just a second or so to retrieve them from the database, it may take some time to load into your browser.
