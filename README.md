# Final Project for Computer Science 110 Lab
Michael Kudrik    
December 2023
---
## Demo


https://github.com/user-attachments/assets/0726e04c-893e-44fb-8c01-33bd21d93be3



## Table of Contents
- [About](#about)
- [How to Run](#how-to-run-the-jar)
- [Installing Maven, globally](#installing-maven-globally)
- [Running using Maven](#running-using-maven)
- [Building the jar](#building-the-jar)
- [License](#license)

## About
This is a basic [Gongfu tea](https://en.wikipedia.org/wiki/Gongfu_tea) timer application written in Java, with Maven. 

## How to run the jar
To run, simply cd into this project directory and then cd into `target/`.

```bash
cd path/to/finalproject/
cd target/
```

Then, run the jar file.

```bash
java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml -jar .\finalproject-1.0.jar
```

Replace `$PATH_TO_FX` with the path to your JavaFX installation.

## Installing Maven, globally
<details>
  <summary>Click here to see how to install Maven globally.</summary>

To install Maven, first download it [here](https://maven.apache.org/download.cgi) (I recommend
the binary zip archive).

Then, unzip the archive.

1. Press Windows key, type `adva`, and click on the
   `View advanced system settings` option.
   
    ![screenshot of system settings](https://mkyong.com/wp-content/uploads/2009/11/install-maven-windows-1.png)

2. In System Properties dialog, select `Advanced` tab
   and click on the `Environment Variables...` button.

    ![screenshot of environment variables](https://mkyong.com/wp-content/uploads/2009/11/install-maven-windows-2-1.png)

3. In “Environment variables” dialog, `System variables`, click on the
   `New...` button
   and add a `MAVEN_HOME` variable and point it to the
   Maven folder.
   
    ![screenshot of setting Maven_HOME variable](https://mkyong.com/wp-content/uploads/2009/11/install-maven-windows-2-2.png)

4. In system variables, find `PATH`, click on the
   `Edit...` button. In “Edit environment variable”
   dialog, click on the `New` button and add this
   `%MAVEN_HOME%\bin\`.

    ![screenshot of editing PATH variable](https://mkyong.com/wp-content/uploads/2009/11/install-maven-windows-3.png)

5. Open a new command prompt and type `mvn -version` to
   verify the installation.

```text
C:\Users\mike>mvn --version
Apache Maven 3.9.6 (bc0240f3c744dd6b6ec2920b3cd08dcc295161ae)
Maven home: C:\Apache\Maven
Java version: 21.0.1, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-21
Default locale: en_US, platform encoding: UTF-8
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"

C:\Users\mike>echo %MAVEN_HOME%
C:\Apache\Maven

C:\Users\mike>
```

</details>

## Running using Maven
To run using Maven, simply run the following command in the root directory of this project.

```bash
cd path/to/finalproject/
mvn package
```

## Building the jar
To build the jar, simply run the following command in the root directory of this project.

```bash
cd path/to/finalproject/
mvn clean package
```

Then, cd into the target directory.

```bash
cd target/
```

Then, run the jar file.

```bash
java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml -jar .\finalproject-1.0.jar
```

Replace `$PATH_TO_FX` with the path to your JavaFX installation.
