# music-share
A music and playlist-sharing app made using Spring Boot, React Native, and Spring boot framework.
Project carried out using TDD for unit, integration tests. Still under development.

## Motivation
 Doing the project to:
 - practice complex Java logic.
 - how to carry out complex testing and mocking.
 - how to make critical decision on what to be developed when.

## Usage
Application allows users to stream music and share playlists.Listeners can rate and comment on shared playlists.

## Technologies
- Spring boot
- React-native(using TypeScript) on the frontend.
- Postgres database.
- Junit for comprehensive unit and integration testing.
- Jest for the frontend React code testing.
- H2 database for mocking in integration testing.
- Mockito for mocking integration and end-to-end tests.

## How to run the application 
-  ```mvn spring-boot:run ```
## How to run the tests
-  First compile the tests  using command ```$ javac -d target -cp target:junit-platform-console-standalone-1.7.2.jar src\test\java\com\musicShare\musicShare\repositories\UserDetailsRepository\*.java```
-  Run all the tests using the command, ```$ java -jar junit-platform-console-standalone-1.7.2.jar --class-path target --select-package com.baeldung.musicShare ```



