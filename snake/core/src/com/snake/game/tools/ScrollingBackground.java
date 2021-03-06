package com.snake.game.tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.snake.game.SnakeSmash;

/**
 * Scrolling background class
 */

public class ScrollingBackground {

    public static final int DEFAULT_SPEED = 80;
    public static final int ACCELERATION = 50;
    public static final int GOAL_REACH_ACCELERATION = 200;

    Texture image;

    float y1,y2;
    int speed;
    int goalSpeed;
    float imageScale;
    boolean speedFixed;

    /**
     * Scrolling background constructor
     */

    public ScrollingBackground(){
        image= new Texture("background.png");
        y1 =0;
        y2= image.getHeight();
        speed = 0;
        goalSpeed = DEFAULT_SPEED;
        imageScale = SnakeSmash.WIDTH / image.getWidth();
        speedFixed = true;
    }

    /**
     * Function to update and render background
     * @param deltaTime delta time
     * @param batch sprite batch
     */
    public void updateAndRender(float deltaTime, SpriteBatch batch){

        if (speed < goalSpeed) {
            speed += GOAL_REACH_ACCELERATION * deltaTime;
            if (speed > goalSpeed)
                speed = goalSpeed;
        } else if (speed > goalSpeed) {
            speed -= GOAL_REACH_ACCELERATION * deltaTime;
            if (speed < goalSpeed)
                speed = goalSpeed;
        }

        if (!speedFixed)
            speed += ACCELERATION * deltaTime;

        y1 -= speed * deltaTime;
        y2 -= speed * deltaTime;

        //if image reached the bottom of screen and is not visible, put it back on top
        if (y1 + image.getHeight() * imageScale <= 0)
            y1 = y2 + image.getHeight() * imageScale;

        if (y2 + image.getHeight() * imageScale <= 0)
            y2 = y1 + image.getHeight() * imageScale;

        //Render
        batch.draw(image, 0, y1, SnakeSmash.WIDTH, image.getHeight() * imageScale);
        batch.draw(image, 0, y2, SnakeSmash.WIDTH, image.getHeight() * imageScale);
    }

    /**
     * Function to set fixed speed
     * @param speedFixed speed fixed to be setted
     */
    public void setSpeedFixed (boolean speedFixed) {
        this.speedFixed = speedFixed;
    }

    /**
     * Function to resize image
     * @param width
     */
    public void resize(int width){
        imageScale=width/image.getWidth();
    }
}
