package com.snake.game.tools;

/**
 * Position class
 */
public class Position {
    float x, y;

    /**
     * Position constructor
     * @param x x coordinate
     * @param y y coordinate
     */
   public Position(float x, float y){
        this.x=x;
        this.y=y;
    }

    /**
     * Function to get y coordinate
     * @return y coordinate
     */
    public float getY(){
        return y;
    }

    /**
     * Function to get x
     * @return x coordinate
     */
    public float getX(){
        return x;
    }
}
