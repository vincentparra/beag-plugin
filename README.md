The Development of BEAG: A GitHub Action Plugin for Identifying Artificial Inteligence Signature in Git Code Commits For Java-based Project

How to use the plugin locally
---
### Step 1: Clone the Branch

Clone this branch to your local machine using:

```bash
git clone https://github.com/vincentparra/beag-plugin.git
```
---
### Step 2: Build and Install the Plugin

Navigate to the cloned project directory, on the terminal run:

```bash 
mvn clean install 
```

Wait for the build to complete successfully. This will install the plugin to your local Maven repository.
---
### Step 3: Use the Plugin in Your Local Project

In your target project’s pom.xml, add the plugin under the <plugins> section and configure it as needed.
Then, run the plugin using Maven commands:

```bash 
mvn beag:detect 
```

### How to use the released version of the plugin
---
### Step 1: Add the JitPack Repository

In your project’s pom.xml, add the JitPack repository inside the <repositories> section (if not already present):

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
---
### Step 2: Add the BEAG Plugin

Next, include the BEAG plugin under the <build> → <plugins> section of your pom.xml:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.github.vincentparra</groupId>
            <artifactId>beag-plugin</artifactId>
            <version>1.0.0</version> <!-- Replace with the latest version -->
            <executions>
                <execution>
                    <goals>
                        <goal>detect</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
---
You can check for the **latest release version** of the plugin here:
 
[![](https://jitpack.io/v/vincentparra/beag-plugin.svg)](https://jitpack.io/#vincentparra/beag-plugin)
---
### Step 3: Run the Plugin

Once added, you can execute the BEAG plugin using the following Maven command:

```bash 
mvn beag:detect 
```
---
## Contributors
- [Vincent Jovan Parra](https://github.com/vincentparra) — Project Lead, Developer, Researcher and Implementation Contributor
- [Melbert Lance Caintic](https://github.com/lancecaintic) — Researcher and Implementation Contributor
- [Vince Sarmiento](https://github.com/Vince-1221) — Researcher and Implementation Contributor
---
## Citation
If you use BEAG in your research or publication, please cite it as:

> Parra, V. J., Caintic, M. L., & Sarmiento, V. (2026). *THE DEVELOPMENT OF BEAG BLOCKING AI GENERATED CODE: A GITHUB ACTION PLUGIN FOR IDENTIFYING ARTIFICIAL INTELLIGENCE SIGNATURES IN GIT CODE COMMITS FOR JAVA-BASED PROJECTS.*

---

## Notes
- Ensure you are connected to the internet when using the JitPack repository.
- For local testing, verify that your `~/.m2/repository` contains the installed BEAG plugin after running `mvn clean install`.

