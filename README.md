## Building the app

```bash
mvn clean package
```
will compile the app, run the unit tests and produce two jar files in the target folder:
the ordinary one and an uber jar, named *-shaded.jar, with all dependencies included.

## Running with maven

```bash
mvn exec:java
```
will run the app from the compiled classes with the `default` spring profile.

```bash
mvn -Dspring.profiles.active=init exec:java
```
will run it with the `init` profile which loads some contacts from resources.

## Running with shaded jar

```bash
java -jar target/contacts-1.0-SNAPSHOT-shaded.jar
```
will run the app with the `default` profile.

```bash
java -Dspring.profiles.active=init -jar target/contacts-1.0-SNAPSHOT-shaded.jar
```
will run it with the `init` profile.

## Available commands

The app allows adding and removing contact details to a contact list.
Enter the `help` command in the app to see the list of available commands with their short description.
For example, when running with the `init` profile, you may type the `show` command to see what contacts
were loaded initially.

## Configuration

The app reads its configuration from the `src/main/resources/application.properties` file.
In addition, when running with the `init` profile, it also reads the
`src/main/resources/application-ini.properties` file.
You may override any of the parameters from the command line using the `-D` flag similar to the examples above.

The following configuration parameters govern the behavior of the app

* `spring.profiles.active` - specifies the spring profiles that are active on start-up.
Valid values are `default` and `init`. If unspecified, `default` is assumed.
* `app.save.path` - used by the `save` command. By default, it's set to `contacts.txt`.
* `app.load.path` - where the contacts are loaded from on start-up with the `init` profile.
By default, it's set to `src/main/resources/default-contacts.txt`.
