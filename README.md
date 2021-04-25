# RoboRally - Unsinkable II
A java implementation of the board game RoboRally for the INF112 subject at the
University of Bergen.

[![Build Status](https://travis-ci.com/inf112-v21/Unsinkable-II.svg?branch=Development)](https://travis-ci.com/inf112-v21/Unsinkable-II)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/ea05dfd3af3c4795b820313d62efec9f)](https://www.codacy.com/gh/inf112-v21/Unsinkable-II/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=inf112-v21/Unsinkable-II&amp;utm_campaign=Badge_Grade)
## Table of Contents

1.  [Overview](#overview)

2.  [Building](#building)
    1.  [Prerequisites](#pre-requisites)
    2.  [Command line](#command-line)
    3.  [Intellij IDEA](#intellij-idea)
    4.  [Running](#running)

3.  [Developing](#developing)
    1.  [Testing](#testing)
    2.  [How To Run](#how-to-run)
    3.  [Debugging](#debugging)

4.  [Known Bugs and Issues](#known-bugs-and-issues)
    1.  [Missing Features](#missing-features)

5.  [The Unsinkable-II Team](#the-unsinkable-ii-team)
    1.  [Developers](#developers)
    2.  [About Us](#about-us)

6.  [Documents](#documents)
    1.  [Diagrams](#diagrams)
    2.  [User Stories](#user-stories)
    3.  [Minutes from meetings](#meeting-minutes)
    4.  [Retrospects](#retrospect)
    5.  [Contributing to Git](#git)
    6.  [Board Design Protocol](#board-design)

## Overview
This project is an approximation of the original version of roborally, a tabletop board game,
in Java using libGDX and Tiled game framework and Kryonet for LAN/WAN multiplayer.

## Building
There are no prebuilt binaries as of now, here are instructions on how to build this project.
Maven shade is included to shade in needed dependencies to the resulting jar file.

### Pre-Requisites
Java 13+
  
[Maven](https://maven.apache.org/) (for CLI usage)

### Command line
```shell
$ mvn package
```

### Intellij IDEA
IDEA should download and install Maven automatically.
Double-click on the package item under lifecycle, found
one the right side under the Maven tab.  
![maven package](https://user-images.githubusercontent.com/3050747/109568552-5d858e80-7ae7-11eb-97d3-b623bf9b669a.png)

### Running
After a successful build, you can run the jar
```shell
java -jar roborally.jar
```

## Developing
Simply open the pom.xml file in any Java IDE like Intellij
IDEA. 

Documentation on contributing using git, see [Git Contributing Guidelines](documentation/GitContributibuting.md).

Documentation on map design, see [Board Design Protocol](documentation/BoardDesign.md).

### Testing
To run the automated tests, simply run `mvn test` from the command line, or run the test lifecycle from an IDE.   

While developing, run the tests from your IDE.

### How to run
Run [Main](src/main/java/roborally/Main.java) from an IDE.

### Debugging
Debugging can be customized by changing values in Debugging.java.
To enable various debugging levels set the corresponding area to debug to true.

## Known Bugs and Issues
See [Issues](https://github.com/inf112-v21/Unsinkable-II/issues)

### Missing Features
Continue power down

Robot spawn direction

Pushers

Special tiles

## The Unsinkable-II Team

### Developers
[Inge Amdal Halvorsen](https://github.com/Snowsock)

[Vegard Haugland](https://github.com/hauglandvegard)

[Daniel Liland](https://github.com/ende124)

[Jonas Valen](https://github.com/jonazbot)

### About Us
[Unsinkable-II](documentation/deliverables/Assignment1/about.md)

[Group dynamics](documentation/deliverables/Assignment1/ComplulsoryAssignment1.md)

## Documents

### Diagrams
[Class Diagram](documentation/deliverables/Assignment3/CurrentClassDiagram.pdf)

[Project board](https://github.com/inf112-v21/Unsinkable-II/issues)

### User Stories
See [User Stories Repository](https://github.com/inf112-v21/Unsinkable-II/projects)

### Meeting Minutes
[Feb/01/2021](documentation/deliverables/MinutesOfMeetings/2021.02.01.md)

[Feb/04/2021](documentation/deliverables/MinutesOfMeetings/2021.02.04.md)

[Feb/08/2021](documentation/deliverables/MinutesOfMeetings/2021.02.08.md)

[Feb/11/2021](documentation/deliverables/MinutesOfMeetings/2021.02.11.md)

[Feb/15/2001](documentation/deliverables/MinutesOfMeetings/2021.02.15.md)

[Feb/25/2001](documentation/deliverables/MinutesOfMeetings/2021.02.25.md)

[Mar/01/2001](documentation/deliverables/MinutesOfMeetings/2021.03.01.md)

[Mar/08/2001](documentation/deliverables/MinutesOfMeetings/2021.03.08.md)

[Mar/15/2001](documentation/deliverables/MinutesOfMeetings/2021.03.15.md)

[Mar/22/2001](documentation/deliverables/MinutesOfMeetings/2021.03.22.md)

### Retrospect
[Retrospect for sprint 2](documentation/deliverables/Assignment2/Retrospect.md)

[Retrospect for sprint 3](documentation/deliverables/Assignment3/Retrospect.md)

### Git
See [Git Contributing Guidelines](documentation/GitContributibuting.md)

### Board Design
See [Board Design Protocol](documentation/BoardDesign.md)