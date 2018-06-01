## LPOO - Final Project Delivery
### Snake Smash


### **1. Setup**
___
a. Project Setup

    Import the project as a gradle project.

b.	App installation procedure

    Install the .apk file in the executables folder



### **2. Project Features**
___
    - fully-functional Graphical User Interface
    - unit testable and have a good test suite
    - Mobile (touch gestures and vibration)
    - Social (using facebook)



###  **3. UML Diagram**
___
![34200981_1886874938001116_8016746308423385088_n](https://user-images.githubusercontent.com/28363713/40865703-99240b5a-65f1-11e8-96eb-9927fc90617a.png)





###  **4. Design Patterns**
___
a. Sigleton - Ensures that a class has only one instance, and provides a global point of access to it. In this case was used in the gameModel and gameController classes.

b. Template -  Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure. In this case many functions are overrided.

c. Update - The game world maintains a collection of objects. Each object implements an update method that simulates one frame of the object’s behavior. Each frame, the game updates every object in the collection. In this case, there are squares, snake , balls and coins that are updated each frame.





###  **5.	User Manual**


#### a.  Main Menu
___
 ![mainmenu](https://user-images.githubusercontent.com/28363713/40864571-3cd1f434-65ec-11e8-9eb4-402847bb668c.PNG)



This is the main menu. The player can choose to play between three existent difficulties (beginner, intermediate and impossible). In this menu the player can also go to the settings menu, go see the current highest score in the game, login in Facebook and close the app.



#### b. Settings Menu
___
![options menu](https://user-images.githubusercontent.com/28363713/40864653-9928f4d0-65ec-11e8-9049-e877323b1586.PNG)


In the settings menu the user is able to enable/disable the music of the game and the vibration when the snake collides with the squares (android feature). We can also return to the main menu or just close the app.



#### c.  Game
___

![gameview](https://user-images.githubusercontent.com/28363713/40864904-b9018b2c-65ed-11e8-9adc-c1bbe5eb325f.PNG)


The objective of the game is to get the largest Snake possible. When the snake collides with a circle, a ball will be added to the snake body. Then, when colliding with a square, the snake lose the number of circles shown inside the box and the game score increases. The player will lose the game if the snake disappears (number of balls = 0). In the game, the player may also collect coins, that will increase the game score and give the player the chance to continue the current game after losing. The number of coins gained and the current score is shown in the upper right side of the screen. The user is also able to pause/continue the game by touching the button in the upper left side of the screen. 


#### d. Game Over
___
![gameovernotcoins](https://user-images.githubusercontent.com/28363713/40865014-2d16dca6-65ee-11e8-9e8a-3bfe1dc14b66.PNG)  ![gameovercoins](https://user-images.githubusercontent.com/28363713/40865022-3e79811a-65ee-11e8-9205-e48541b34db8.PNG)  ![newhighscore](https://user-images.githubusercontent.com/28363713/40865039-4ceba85e-65ee-11e8-8da7-4d9ea58a5b68.PNG)


When losing the game, the score achieved by the player is shown in the screen, telling if the player achieved the highest score ever reached in the game or not. Having 5 or more coins the player is able to continue playing with the same score. The player can also restart the game (in the same difficulty), go back to the main menu or just exit the game.


#### e. High Score Menu
___
![highscore](https://user-images.githubusercontent.com/28363713/40865107-af64a382-65ee-11e8-995c-a3366b060661.PNG)


This menu shows the current highest score in the game. The user can go back to the main menu or exit the app.


#### f. Facebook login
___
![34074389_1886873054667971_7220525119104352256_n](https://user-images.githubusercontent.com/28363713/40865282-7421419e-65ef-11e8-8ef2-c22ab74297d8.png)


The user can login to facebook. Also made function to publish the score of the player, but couldn't publish due to new facebook permissions...


### **6.   Difficulties**
___


Our main difficulty was without a doubt implementing facebook. Most of the documentations we found were all outdated and not correct, causing us numerous problems with the gradle dependencies and also with trying to import facebook sdk. Eventually we found a workable api "restfb" which finally allowed us to login to facebook. However, even though we tried everything, all of the permissions to publish to facebook were removed, being impossible to implement that feature into our game.



### **7.   Overal time and work distribution**
___



### **8.   Credits**
___

For our project we researched a lot and implemented some things we found. Here's the assets we used:
https://restfb.com/documentation/

https://github.com/libgdx/libgdx/wiki/Preferences

http://manabreak.eu/java/2016/10/21/unittesting-libgdx.html
