The Development of BEAG: A GitHub Action Plugin for Identifying Artificial Inteligence Signature in Git Code Commits For Java-based Project

RELEASE: 
[![](https://jitpack.io/v/vincentparra/beag-plugin.svg)](https://jitpack.io/#vincentparra/beag-plugin)

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
