# ♕ BYU CS 240 Chess

Chess project for BYU CS 240

## IntelliJ Support

Open the project directory in IntelliJ and you will be able to develop, run, and debug your code using an IDE.

## Maven Support

You can use the following commands to build and package your code.

| Command                   | Description                              |
| ------------------------- | ---------------------------------------- |
| `mvn compile`             | Builds the code                          |
| `mvn package`             | Run the tests and build an Uber jar file |
| `mvn package -DskipTests` | Build an Uber jar file                   |
| `mvn exec:java`           | Build the code and run `Main`            |

How these commands execute are defined by the `pom.xml` file. It also contains dependency definitions for all the packages required to complete the Chess project.

### Running the program using Java

Once you have compiled your project into an uber jar, you can execute it with the following command.

```sh
java -cp target/chess-jar-with-dependencies.jar Main
```
