# iMath : Node Editor
![Version](https://img.shields.io/badge/Version-v1.0.1-green)

## Table of Contents  
[Documentation](#documentation)  
[Getting Started](#gettingStarted)
[Features](#features)  
[Functions](#functions)  

<a name="documentation"/>

## Documentation
Documentation is generated using javadoc and is available at: [Documentation](https://imath.antoineh.tech)

<a name="gettingStarted"/>

## Getting started
### Dependencies
[![JDK14](https://img.shields.io/badge/JDK-14-green)](https://jdk.java.net/14/) [![JavaFX14](https://img.shields.io/badge/JavaFX-14-green)](https://gluonhq.com/products/javafx/)

iMath is using the following dependencies:
- JDK version **14** [available here](https://jdk.java.net/14/)
- JavaFX version **14** [available here](https://gluonhq.com/products/javafx/)

### Downloading the repository
`git clone git@github.com:antoineheseque/iMath-NodeEditor.git`

Or click the `Clone or Download` button on the right.

### IDE
We used **IntelliJ idea** [(download here)](https://www.jetbrains.com/fr-fr/idea/download/#section=windows).
If you got any errors, make sure you are using the **Open JDK 14 v14.0.1** under `File>Project Structure>Project>Project SDK` and **14 - Switch expressions** under `Project language level` in the same menu.
Please verify if the JavaFX library is imported, and all the parameters are set in the **Build Configuration**. [(Tutorial here)](https://www.jetbrains.com/help/idea/javafx.html).

Also add `javafx.web` in the `--add-modules` in the VM Configuration so it should look like this:
`--module-path %JavaFX/lib path% --add-modules=javafx.controls,javafx.fxml,javafx.web`

### Build / Run
Click the build or run button to launch the application.

<a name="features"/>

## Features
The features below can use many functions listed in the [Functions](#functions) category.

### Simple equation


### Parametric equations


<a name="functions"/>

## Functions
### Mathematics