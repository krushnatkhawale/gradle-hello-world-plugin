# Hello World Gradle Plugin ( Java )

This is a very basic example of creating a custom gradle plugin using Java.

Let's get started with creating a gradle plugin.

### 1. Create a java-gradle application

![1-empty-project.png](docs/images/1-empty-project.png)

As you can see, above is an empty java gradle project. Two things to notice here,

1. there is only java plugin applied (so far)
2. there is only java as a library under 'External Libraries' (In project window on the left), so far


### 2. Configure the project as gradle plugin
 
The project can be configured as gradle plugin by using the plugin `java-gradle-plugin` under plugins section in `build.gradle` file as follows
```groovy
plugins {
    id 'java-gradle-plugin'
}
```
![2-use-standard-java-gradle-plugin.png](docs/images/2-use-standard-java-gradle-plugin.png)

The screenshot above highlights things to notice such as,

1. `java-gradle-plugin` is applied ( line number 3 in build.gradle )
2. As soon as project is refreshed, the **External Libraries** will contain required gradle-api and gradle kit for plugin development
3. When Gradle's `clean build` tasks are run, notice gradle plugin related tasks appeared and got run (highlighted in logs) 
4. Now that plugin tasks are added and run, observe point 4 where a warning is logged about there is no plugin descriptor found


### 3. Add a plugin descriptor

A plugin descriptor, describes the name of the plugin and the class implementing the plugin.
There are two ways to add a plugin descriptor (a) a properties file at **META-INF/gradle-plugins** in the resources directory (b) using Gradles `gradlePlugin` descriptor in build.gradle
This example is using `gradlePlugin` to describe the plugin.

![3-configure-plugin-class.png](docs/images/3-configure-plugin-class.png)

As highlighted in the screenshot,

1. `helloWorld` is the name of the gradle task that can be run using gradlew command as `./gradlew helloWorld`
2. id `com.krushnatkhawale.helloworld` is groupid of the plugin, can also be used when adding dependency or while applying as plugin
3. implementationClass `HelloWorldPlugin` is class which will contain plugin code
4. After gradle refresh and gradle's clean build tasks are successful, notice the plugin descriptor warning from log is gone
5. But this has introduced new warning which then warns about another issue of **HelloWorldPlugin class not found**

### 4. Add Plugin class

In previous step, the plugin descriptor specified the plugin class so same class needs to be present at the same package that has been specified in plugin descriptor as shown below

![4-add-plugin-class.png](docs/images/4-add-plugin-class.png)

So after adding the **HelloWorldPlugin** class to **com.krushnatkhawale.helloworld** package and Gradle's clean build tasks are run notice the class not found error has gone

### 5. Add a task to the plugin

Before adding a task, it's important to understand the difference between Gradle's **Task** and **Plugin**

#### Plugin : A plugin typically affects the entire build process and can introduce/have multiple tasks and configuration options
#### Task &nbsp; &nbsp; : A task is a single unit of work within the build process

Now with that difference in mind, let's add a Task (which will perform an action) and map it to a plugin (which can have more that one task)

A task can be created by extending **DefaultTask** class and which will have a method indicated as an action using `@TaskAction` annotation. Below task simply prints 'HELLO WORLD' message in the action method.

![5-add-task-to-plugin.png](docs/images/5-add-task-to-plugin.png)

Once the task is added, it needs to be configured to be the part of the plugin use `create` method of `TaskContainer` class as shown in the image above (point 5 in the image). Now that the plugin and task is configured, let's run `gradlew clean build` command to ensure nothing is broken.
Now let's see how it can be configured in other applications.

### 6. Use the plugin in other applications

To use and execute the `helloWorld` plugin, lets create a new application (gradle project) and configure the plugin

![6-configure-plugin-in-client-app.png](docs/images/6-configure-plugin-in-client-app.png)

As you see in screenshot above, in the `build.gradle` of this new project, our plugin is configured to be used with the `id` used `com.krushnatkhawale.helloWorld` is the same id that is configured in step 3 using plugin descriptor and the `version` is the version published by step 5, but as soon as you perform a gradle refresh, notice the error plugin was not found.

![7-enable-maven-publishing.png](docs/images/7-enable-maven-publishing.png)
![8-point-maven-repo-to-local.png](docs/images/8-point-maven-repo-to-local.png)
![9-run-plugin-success.png](docs/images/9-run-plugin-success.png)