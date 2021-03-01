# RoboRally - Unsinkable II
A java implementation of the board game RoboRally for the INF112 subject at the
University of Bergen.

## Table of Contents
1. [Overview](#overview)
2. [Building](#building)
    1. [Prerequisites](#prerequisites)
    2. [Command line](#comand-line)
    3. [Intellij IDEA](#intellij-idea)
    4. [Running](#running)
3. [Developing](#developing)
   1. [Testing](#testing)
4. [Developers](#developers)
5. [Documents](#documents)

## Overview
This project's goal is to make a fully functional and playable version of
the board game RoboRally in Java with the libgdx game framework. Kryonet is used
for networking to enable online play.

## Building
There are no prebuilt binaries as of now, here are instructions on how to build
this project.
Maven shade is included to shade in needed dependencies
to the resulting jar file.

### Prerequisites
- Java 13+
- [Maven](https://maven.apache.org/) (for CLI usage)

### Comand line
```shell
$ mvn package
```

### Intellij IDEA
IDEA should download and install Maven automatically.
Double click on the package item under lifecycle, found
one the right side under the Maven tab.  
![maven package](https://user-images.githubusercontent.com/3050747/109568552-5d858e80-7ae7-11eb-97d3-b623bf9b669a.png)

### Running
After a successful build, you can run the jar
```
java -jar RoboRally.jar
```

## Developing
Simply open the pom.xml file in any Java IDE like Intellij
IDEA. Make sure to follow the 
[contributing guidelines](./documentation/CONTRIBUTING.md).

For documentation on map design, see [MapDesign.md](./documentation/MapDesign.md).

### Testing
To run the automated tests, simply run `mvn test` from the command line,
or run the test lifecycle from an IDE.  
While developing, run the tests from your IDE.

## Developers
Current developers of this project
- [Daniel Liland](https://github.com/ende124)
- [Inge Amdal Halvorsen](https://github.com/Snowsock)
- [Jonas Valen](https://github.com/jonazbot)
- [Vegard Haugland](https://github.com/hauglandvegard)

## Documents
For further documents related to this project and assignments, see
[documents.md](./documentation/documents.md).
