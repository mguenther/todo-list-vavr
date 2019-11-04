# Functional Application Design with VAVR

[![Build Status](https://travis-ci.org/mguenther/todo-list-vavr.svg?branch=master)](https://travis-ci.org/mguenther/todo-list-vavr.svg)

This repository contains an example application that showcases how to use VAVR's `Option`, `Try` and `Either` monads to design an application in a functional style. The example application is a super simple todo list, so complex business logic doesn't get in the way.

## Getting Started

You can build and run the application using `mvn spring-boot:run`.
 
### Open API UI
 
The application comes with an OpenAPI integration that is available at [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) once the application has successfully started. The API is documented, so you should be able to figure things out for yourself.

### H2 Console

The example application has the H2 console configured. You can take a look at the database at [http://localhost:8080/h2-console](http://localhost:8080/h2-console), connect the JDBC URL `jdbc:h2:mem:testdb`. The credentials for accessing the database are `sa:password`.

## License

This work is released under the terms of the Apache 2.0 license.