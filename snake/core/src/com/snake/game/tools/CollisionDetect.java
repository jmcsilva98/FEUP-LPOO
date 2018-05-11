package com.snake.game.tools;

public class CollisionDetect {
    float x, y;
    int width, height;
    public CollisionDetect(float x, float y,int width,int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    public void move (float x, float y){
        this.x=x;
        this.y=y;
    }
    public boolean collidesWith(CollisionDetect collision){
        return x<collision.x+collision.width && y < collision.y +collision.height && x +width > collision.x && y+height > collision.y;
    }

}
