# Elevator Application

Elevator is a Spring Boot project that emulates the intricate dynamics of elevator movement within a building,
while also providing an immersive experience in mastering elevator operation.
### Prerequisites
The following  should be installed in your system:
* [Java 20 or newer](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Git command line tool](https://help.github.com/articles/set-up-git)

* Your preferred IDE
    * Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in `Help -> About` dialog. If m2e is
      not there, just follow the install process here: https://www.eclipse.org/m2e/
    * [Spring Tools Suite](https://spring.io/tools) (STS)
    * IntelliJ IDEA
    * [VS Code](https://code.visualstudio.com)

## Database configuration
* The project uses an in-memory `H2` database therefore no configuration is required.
* A helper `DataInit.java` class is included to help create test data.

## Running Elevator App locally
Elevator is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Gradle](https://spring.io/guides/gs/gradle/). You can build a jar file and run it from the command line:


```
git clone https://github.com/NdiranguMuchai/elevator.git
cd elevator
./gradlew package
java -jar target/*.jar
```
You can then access maker checker here: http://localhost:8080/

Or you can run it from Maven directly using the Spring Boot Gradle plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```
./gradlew bootRun
```

### Gaps
* The elevators do not move to the destination floors accurately.
* Logging is only upon request of an elevator.
