# RoboRally - Unsinkable II
A java implementation of the board game RoboRally for the INF112 subject at the
University of Bergen.

[![Build Status](https://travis-ci.com/inf112-v21/Unsinkable-II.svg?branch=Development)](https://travis-ci.com/inf112-v21/Unsinkable-II)

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
6. [About](#about)
7. [Minutes from meetings](#meeting-minutes)
8. [User Stories](#user-stories)
9. [Diagrams](#diagrams)
10. [Retrospect](#retrospect)
11. [Contributing to Git](#git)
12. [Debugging](#debugging)
13. [Features](#implemented-features)

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

## How to run:
Run 
[Main](src/main/java/RoboRally/Main.java)
from IDE.

## Known bugs:
See [issues](https://github.com/inf112-v21/Unsinkable-II/issues)

## Contributing:

### About

[Unsinkable-II](documentation/deliverables/Assignment1/about.md)

[Group dynamics](documentation/deliverables/Assignment1/ComplulsoryAssignment1.md)

### Meeting Minutes
[Feb/01/2021](documentation/deliverables/MinutesOfMeetings/2021.02.01.md)

[Feb/04/2021](documentation/deliverables/MinutesOfMeetings/2021.02.04.md)

[Feb/08/2021](documentation/deliverables/MinutesOfMeetings/2021.02.08.md)

[Feb/11/2021](documentation/deliverables/MinutesOfMeetings/2021.02.11.md)

[Feb/15/2001](documentation/deliverables/MinutesOfMeetings/minutes_15.02.21.md)

[Feb/25/2001](documentation/deliverables/MinutesOfMeetings/minutes_25.02.21.md)

[Mar/01/2001](documentation/deliverables/MinutesOfMeetings/minutes_03.01.21.md)

[Mar/08/2001](documentation/deliverables/MinutesOfMeetings/minutes_08.03.21.md)

[Mar/15/2001](documentation/deliverables/MinutesOfMeetings/minutes_15.03.21.md)

[Mar/22/2001](documentation/deliverables/MinutesOfMeetings/minutes_22.03.21.md)

### User Stories
User Stories is located online, at
[User Stories reposatory](https://github.com/inf112-v21/Unsinkable-II/projects)

### Diagrams
[Proposed RoboRally.Game flow](documentation/deliverables/Assignment1/RoboRally%20Flowchart.pdf)

[Project board](https://github.com/inf112-v21/Unsinkable-II/issues)

### Retrospect
[Retrospect for sprint 2](documentation/deliverables/Assignment2/Retrospect.md)

[Retrospect for sprint 3](documentation/deliverables/Assignment3/Retrospect.md)

### Git
[Git instructions](documentation/CONTRIBUTING.md)

### Debugging
In RoboRallyApp enable debugging by setting public static final boolean DEBUG = false; => public static final boolean DEBUG = true;
Debugging can be customized by changing values in Debugging.java

###Implemented features 
- Game board is visible.
- Pieces shown for all players.
- Pieces can be moved.
- Pieces can visit flags.
- Multiple of machines can play at the same time.
- Cards can be dealt to players.
- Player can choose 5 of 9 cards.
- Pieces move from chosen cards.
- Lasers for Robots and walls, but no damage.
- Walls that stops robots and lasers.
- Gears that rotate a robot.
- Power down button.

