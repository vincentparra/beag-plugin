The Development of BEAG: A GitHub Action Plugin for Identifying Artificial Inteligence Signature in Git Code Commits For Java-based Project

How to use the plugin locally

Step 1: Clone the Branch

Clone this branch to your local machine using:

`git clone <repository-url> -b <branch-name>`

Step 2: Build and Install the Plugin

Navigate to the cloned project directory, then run:

`mvn clean install`


Wait for the build to complete successfully. This will install the plugin to your local Maven repository.

Step 3: Use the Plugin in Your Local Project

In your target project’s pom.xml, add the plugin under the <plugins> section and configure it as needed.
Then, run the plugin using Maven commands:

`beag:detect`

How to use the released version of the plugin

Step 1: Add the JitPack Repository

In your project’s pom.xml, add the JitPack repository inside the <repositories> section (if not already present):

`<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>`

Step 2: Add the BEAG Plugin

Next, include the BEAG plugin under the <build> → <plugins> section of your pom.xml:

`<build>
    <plugins>
        <plugin>
            <groupId>com.github.vincentparra</groupId>
            <artifactId>beag-plugin</artifactId>
            <version>1.0.0</version> <!-- or the latest version -->
        </plugin>
    </plugins>
</build>`


You can check for the latest release version here:
 
[![](https://jitpack.io/v/vincentparra/beag-plugin.svg)](https://jitpack.io/#vincentparra/beag-plugin)

Step 3: Run the Plugin

Once added, you can execute the BEAG plugin using the following Maven command:

`mvn beag:detect`

