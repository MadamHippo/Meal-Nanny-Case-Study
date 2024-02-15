# Meal Nanny: Full-Stack Project for Client

Meal Nanny is a community-focused initiative that offers membership to those interested in creating and building little free pantries/libraries in their local area.

YouTube demo: https://youtu.be/wr6XY516rU8

User story and app proposal (Google Slides): https://docs.google.
com/presentation/d/1blk8f0K98wNoSzt6JJYYHBLUYar0-k25ms1zdsfitec/edit?usp=sharing


# Dependencies
The project is built using Java, Spring Boot v3.0+, Hibernate, Thymeleaf, Spring Data JPA, Spring Security, 
Bootstrap, HTML, CSS, and JavaScript. Full list check pom.xml file. Here are summaries of each technology.

Java - a high-level, object-oriented programming language.

Spring Boot - a popular Java-based framework used for building web applications.

Spring MVC - a web framework built on top of the Spring framework that provides a model-view-controller architecture for building web applications.

Hibernate - an open-source object-relational mapping framework used for mapping Java classes to database tables.

Thymeleaf - a modern server-side Java template engine used for building dynamic web pages.

Spring Data JPA - a framework that provides a repository abstraction layer on top of JPA, making it easier to work with data persistence.

MySQL - a popular open-source relational database management system used for storing and managing data in e-commerce applications.

Spring Security - a powerful security framework that provides authentication and authorization support for web applications.

Bootstrap - a popular front-end development framework used for building responsive and mobile-first web applications.

HTML - a markup language used for creating web pages.

CSS - a style sheet language used for describing the presentation of web pages.

JavaScript - a programming language used for creating dynamic web pages.

jQuery - a fast and concise JavaScript library used for simplifying HTML document traversal and manipulation.

Maven - a build automation tool used for managing and building Java projects.



## Installation

To run this project on your local machine, you will need to have Java 11 and Maven installed. Once you have installed these prerequisites, follow these steps:

Clone the repository to your local machine using ```git clone https://github.com/MadamHippo/Meal-Nanny-Case-Study```

Open the project in your favorite IDE or text editor.

Ensure that you have Java 11 or higher installed.

Ensure that you have MySQL installed on your machine.

Update the application.properties file with your MySQL database credentials.

Run the SQL script ecommerce.sql in your MySQL server to create the database schema and insert some sample data.

Build the project using Maven by running mvn clean install.

Run the project using
```
mvn spring-boot:run
```
The project should now be running on http://localhost:8080/MealnannyAdmin/

## Usage

Once the project is running, you can access the home page by visiting http://localhost:8080/MealnannyAdmin/

From there, you can login, browse accounts, view the stocklist details, and submit feedback

```
http://localhost:8080/MealnannyAdmin/
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.
[MIT](https://choosealicense.com/licenses/mit/)

## Acknowledgments

Thank you Udemy.


