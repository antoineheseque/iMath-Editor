# iMath : Node Editor
![Version](https://img.shields.io/badge/Version-v1.1.2-green)

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
Please verify if the JavaFX library is imported, and all the parameters are set in the **Build Configuration**. Follow the steps [(here)](https://www.jetbrains.com/help/idea/javafx.html#add-javafx-lib) and [(here)](https://www.jetbrains.com/help/idea/javafx.html#vm-options).

Also add `javafx.web` in the `--add-modules` in the VM Configuration so it should look like this:
`--module-path %JavaFX/lib path% --add-modules=javafx.controls,javafx.fxml,javafx.web`
  
### Build / Run
Click the build or run button to launch the application.

<a name="features"/>

## Features
The features below can use many functions listed in the [Functions](#functions) category.

### Simple equation
You can enter a function in the f(x) field and calculate it in a specific point. 
For this case of use, you have to enter the x value in the associated field and click on the calculate button. The result will be shown right under the button.

You can also calculate the function in multiple points and show it in a graph. For that you need to set a range for the **X-Axis (from xx to xx)** and set the number of points.
**Since we are using a Thread for all the calculation, you can set the number of points to 10 000 and have fun!**

### Parametric equations
The parametric equations are the same as the simple equation, except the the **f(x)** function describe the function associated to the **X-Axis**, and the **g(x)** function describe the function associated to the **Y-Axis**.

The range from xx to xx points will be applied for both functions.

You can also use the simple calculation so you will get a result for both of the functions.

<a name="functions"/>

## Functions
### Mathematics

>Basic operators: +, -, *, /  
>Complex operators: ^ (power), ( ) (parenthesis)  
Functions:  
>>  ln(a) where a can be equals to x, 10x+23, ...  
>>  sqrt(a)  
>>  exp(a)
### Trigonometry
>cos(a)  
>sin(a)  
>tan(a)  
>sinc(a)  
>pi (= 3.141592653589793)  
