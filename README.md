testing fdfdsa
## Online Banking
A web application for online banking

## Built With

* [Spring Boot](https://projects.spring.io/spring-boot/) - Inversion of Control Framework
* [Bootstrap 5](https://getbootstrap.com/docs/5.0/getting-started/introduction/) - HTML, CSS, and JavaScript framework
* [Thymeleaf](https://www.thymeleaf.org/) - Modern server-side Java template engine
* [Gradle](https://gradle.org/) - Dependency Management and Build Tool
* [React](https://reactjs.org/) - React JS
* [MySQL](https://www.mysql.com/) - Open-source relational database management system
* [AWS](https://aws.amazon.com/) - On-demand cloud computing platforms
* [H2](http://www.h2database.com/) - In-Memory Database for development

## Contributing
Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) 
for details on our code of conduct, and the process for submitting pull requests to us.

## Credit
This project is an updated implementation of [Le Dang](https://github.com/raydeng83/OnlineBanking)'s project. 

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

* The following environment variables need to be configured before application starts:
- For example:

        ADMIN_USERNAME = NAME
        ADMIN_PASSWORD = PASSWORD
        ENCRYPTION_SECRET_SALT = salt
        ENCRYPTION_SECRET_PASSWORD = password
        JWT_SECRET = 33ee9048-716d-4166-b532-43702a756f59
        
* Start Spring Boot application first using - **./gradlew bootRun**
* Start React application using - **npm install** then **npm start**
* Access application on - *http://localhost:3000/*
* Access in-memory database on *http://localhost:8080/console*

## Running on Docker (Assuming docker is installed)
* in the directory where docker-compose.yml file resides, simply run the command - **docker-compose up**

## Stop running application on Docker
* in the directory where docker-compose.yml file resides, simply run the command - **docker-compose down**
