# RealTime Transaction Statistics API

This RealTime Statistics API displays the use of Spring Boot and it Scheduling capabilities. It also demonstrates *Servlet 3.1 based Asynchronous request processing* and friendly error reporting in a REST Api.
___

### The API has the following endpoints: -

* `GET /statistics` -> provides a transaction summary of the last 60 seconds. It's calculated in O(1) time & space complexity ('is constant to the number of secs in a minute'). This can be further improved by using a Min/Max Queue and a always calculated Statistics object of the last 60 seconds (still O(1) complexity). It utilizes Servlet 3.1's asynchronous request processing capabilities.
 
* `POST /transactions` -> create a transaction.

___

The API supports *HATEOAS* for seamless discoverability.

- To run the project cd into the project directory and type `./gradlew clean bootRun` and then browse to `http://localhost:8080` for more information.
