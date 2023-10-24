You can run the app from the command line with `mvn exec:java` or from the IDE.
Enter the `help` command to see the list of available commands with their short description.
The app runs with the `default` spring profile. You can change it to `init` in application.properties file.
In that case, the list of contacts will be loaded from the default-contacts.txt file in resources folder.
You may change the locations of files for loading and saving by changing the `app.save.path`
and `app.load.path` configuration parameters in application.properties and application-init.properties respectively.
