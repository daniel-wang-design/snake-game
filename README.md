<h1>Snake Game</h1>

 A fully implemented executable game created using JavaFX. To run the game, follow the <strong>Installation Instructions</strong> below. 

<strong><h2>Installation Instructions</h2></strong>

Required that you have Java SE Runtime Environment installed. Tested to be working on Java(TM) SE Runtime Environment (build 1.8.0_333-b02).
 
To run the application, simply download the file called "SnakeGameByDaniel.jar" located in the same directory as this README.md file. Alternatively, you can find the executive .jar file located at snake-game > SnakeGameByDaniel > dist. 

You may need to allow administrative privelages in order to run this app. 

<strong><h2>Game Snapshots</h2></strong>

Home screen
<img src=images/home.png>

Game running example
<img src=images/in-game.png>

Game over page
<img src=images/game-over.png>

<strong><h2>Project Breakdown</h2></strong>

## Direction.java
The files contains the enumeration Direction contains 4 constants: UP, DOWN, LEFT, RIGHT. These denote the snake's direction in game. 

## Point.java
This file contains the class Point which includes the <em>x</em> and <em>y</em> coordinates of a Point. A point is used to represent the location of a segment of the snake, the position of food pieces, or the head of the snake. 

## Food.java
This file contains the class Food which handles the Food object. Food objects are the red "apples" that the snake can eat in game. It also makes use of the Point class to store the apple's location and has functions to handle collisions with the snake. 

## Snake.java
This file contains the Snake class which contains all functions and variables related to the snake that the player controls. All movement and collision with the outer edges of the game, an apple, or with the snake itself. It also contains functions that control the snake's movement given a keyboard input. 

## SnakeGameByDaniel.java
This file contains the main class SnakeGameByDaniel which has all code needed to integrate all the aforementioned fles as objects and to produce visual output using JavaFX. JavaFX is set up here using AnimationTimer as well as updating the PrimaryStage as events occur in the game. This class also handles keyboard and mouse input as dictated by built-in listener functions in JavaFX.  




