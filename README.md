## Online Banking
A web application for online banking

## Built With

* [Spring Boot](https://projects.spring.io/spring-boot/) - Inversion of Control Framework
* [Bootstrap 5](https://getbootstrap.com/docs/5.0/getting-started/introduction/) - HTML, CSS, and JavaScript framework
* [Thymeleaf](https://www.thymeleaf.org/) - Modern server-side Java template engine
* [Gradle](https://gradle.org/) - Dependency Management and Build Tool
* [Lombok](https://projectlombok.org/) - Automatically plugs into your editor and build tools, spicing up your java.
* [NextJS](https://nextjs.org/) - The React Framework for production
* [MySQL](https://www.mysql.com/) - Open-source relational database management system
* [AWS](https://aws.amazon.com/) - On-demand cloud computing platforms
* [H2](http://www.h2database.com/) - In-Memory Database for development


## Running Instance on AWS
<a href="http://crestbank.sytes.net/" target="_blank">Crest Bank Inc.</a>

## Contributing
Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) 
for details on our code of conduct, and the process for submitting pull requests to us.

## Credit
This project is an updated implementation of [Le Dang's](https://github.com/raydeng83/OnlineBanking/) project. 

## Authors

* **Eric Opoku** - *Initial work* 
* **George Anguah** - *Initial work* 
* **Matthew Puentes** - *Initial work* 
* **Stephen Boakye** - *Contributor*
* **Charles Dimbeng** - *Contributor*
## Acknowledgments

* To Support
* To learn
* etc


## Notes

* The following environment variables can be customized as necessary.:
- The defaults are:

        ADMIN_USERNAME=admin
        ADMIN_PASSWORD=password
        ENCRYPTION_SECRET_SALT=salt
        ENCRYPTION_SECRET_PASSWORD=password
        JWT_SECRET=salt

* Default profile for the application is **dev** and **prod** to test out production functionalities.
  - Datasource must be provided for production profile for the application to run.

        
* Start Spring Boot application first using - **./gradlew bootRun**
* Start React application using - **npm install** then **npm start**
* Access application on - *http://localhost:3000/*
* Access in-memory database on *http://localhost:8080/console*

## Running on Docker (Assuming docker is installed)
* in the directory where docker-compose.yml file resides, simply run the command - **docker-compose up**

## Stop running application on Docker
* in the directory where docker-compose.yml file resides, simply run the command - **docker-compose down**
