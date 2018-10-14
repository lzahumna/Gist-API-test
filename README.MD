# Project overview
The project uses Maven as build tool. It contains several source packages worth to mention:
* com.lzahumna.dto - contains all the Java representation of Gists API request/response JSON. 
GSON is chosen as serializer/deserializer to use DTO classes while operating with Gists API instead of constant traversal through JSON with RestAssured jsonpath
* com.lzahumna.property.loader - contains PropertyLoader to fetch properties from app.properties file 
(such as token and base URI)
* com.lzahumna.rest.client - contains 3 REST clients for Gists API (CRUD API 
(all the endpoints responsible for gists), Fork API, Star API), their implementations 
and common exception for all of them

## Gists REST clients
Three implemented REST clients cover all the operations mentioned in https://developer.github.com/v3/gists/. 
The main reason to implement them was to isolate all the calls to endpoints from actual tests.
Since Gists API exposes three different kinds of operations (create/get/edit/delete gists, fork/get forks and star/unstar),
I decided to split them into 3 logical categories and put into separate REST clients, which gives easy readable code.
As a trade-off between tests and such REST clients structure, I decided to check status codes in REST clients themselves. 
You lose opportunity to check status codes in tests, but on the other hand Gists API doesn't have lots of status codes (1,2 at max),
so I made assumption if I get not correct status code, it means it was bad request and test should fail. 

## Stack
* Java 8
* RestAssured
* Lombok
* GSON
* JUnit

## Tests 
There are 3 test classes to cover Gists REST API with multiple different test cases including edge cases. 

mvn clean test

## Properties
There are 2 properties:
* github.token - Base 64 Github token with Gist permission
* gists.api.base.uri - Base URI to Gists API

## Comments in the code
There are comments on all of the methods and classes. You can simply look through all of them.

## Future improvements
I implemented most of the features I wanted to. There are some of them left and not implemented, however, worth to be mentioned. Also test summary reporting could be included.

### DTO and REST clients
* Make REST clients return object, which contains received status code and parsed response body.
It should allow to check status codes in tests and cover any related test cases without any limitation.

### Tests
* Experiment with fork gist endpoints to check if a user could have only one fork created from the same gist.
* Some tests require already created gist to test edit/delete etc. Every time the gist is created, which could be considered as overhead
and could be implemented as creating several gists in advance.

### Exception
* Add granular exceptions to return more specific errors back from REST clients