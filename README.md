# coffee_connoisseur_API
<!-- PROJECT LOGO -->
<br />
<div align="center">
  <img src="logo.jpg" alt="Logo" width="250" height="250">

<h3 align="center">Coffee Connoisseur API</h3>

  <p align="center">
    
  </p>
</div>

<!-- ABOUT THE PROJECT -->
## About The Project
Essentially an app where ...
The reason for an app like this is to be able to say:

_“I want to make a coffee let me find a recipe to help make my next coffee.”_

```
App Features: 
* A new user can be registered
* A user can login
* A user can is confirmed
* All coffees can be viewed
* A single coffee can be looked up
* Get a coffee based on tags
* Get a coffee based on ingredients that you have available
* A recipe can be looked up and filtered based on difficulty
* A user can update their rating of a coffee
* A user can rate a coffee
```

### Built With

* Java Spring
* MySQL
* AWS Cloud Formation
* AWS Cognito

<!-- ERD UPLOAD -->
### Entity Relational Diagram
 <img src="ERD.jpg" alt="ERD" width="1000" height="500">

 <!-- API ENDPOINTS-->
 ### API Endpoints
 ```
 * /coffee?sort-by={value} value=rating, difficulty
 * /coffees?tags={tag}&sort-by={value} value=rating, difficulty
 * /coffees?ingredients={ingredient}&sort-by={value}
 * /coffees?difficulty={difficulty}
 * /coffees/updateRating/{rating}
 * /coffees/{id}
 * /register
 * /login
 ```
 
<!-- GETTING STARTED -->
## Getting Started

If you wish to test out the code and run the DB instance, please follow the instructions below

### Prerequisites

* Cloud Formation Stack Endpoint

  _If you wish to run on a cloud service_
  
  _e.g. [AWS](https://bbd-internal-sso.awsapps.com/start#)_

* Application To Run Endpoint
  - Visual Studio or,
  - Command line on Windows.


### Running the DB scripts

1. Create DB: run the file to create DB and its tables
```sh
DBCreation.sql
```

### Running the API's

#### Running the scripts in Windows command line:
1. Open the project in File Explorer and run the following command in terminal:
```sh
mvn spring-boot:run
```

2. In a browser, navigate to 
_https://localhost:port/desired_endpoint
where _port_ is the randomly chosen port number (443) and _desired_endpoint_ is the endpoint that you would like to test on the API.

3. Once the web API has been launched in your favourite web browser the API endpoints can be interacted with either through Postman or the Swagger interface.
 
<!-- MEET THE TEAM -->
## Development Team

- [ ] [Brad Betts]()
- [ ] [Marie van der Merwe]()
- [ ] [Nikita Smal](https://github.com/nikitasmal)
- [ ] [Phumzile Nkosi](https://github.com/PhumzileNkosi)
- [ ] [Razeen Bahadoor](https://github.com/SparklingCouscous)
- [ ] [Thabo Rachidi]()

<!-- REFERENCES -->
## References

* [AWS Documentation](https://docs.aws.amazon.com/)
* [MySQL Documentation](https://dev.mysql.com/doc/)
* [Java Spring Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/)
* [The Hive](https://the-hive.bbd.co.za/)
* [Pluralsight](https://app.pluralsight.com/)
