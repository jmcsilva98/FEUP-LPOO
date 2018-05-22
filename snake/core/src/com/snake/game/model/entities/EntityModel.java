package com.snake.game.model.entities;

public abstract class EntityModel {
    public void setPosition(float x, float y){
        this.x=x++;
        this.y=y++;
    }

    public enum ModelType {SNAKE,PINKSQUARE,WALL,COIN,BALL,YELLOWSQUARE,LIGHTPINKSQUARE,REDSQUARE,MUSTARDSQUARE,BLUESQUARE,GREENSQUARE,ZERO,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT, NINE}

    private float x;
    private float y;
    private float rotation;

    public EntityModel(float x,float y, float rotation){
        this.x=x;
        this.y=y;
        this.rotation=rotation;
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public float getRotation(){
        return rotation;
    }

    public void setX(float x){
        this.x=x;
    }
    public void setY(float y){
        this.y=y;
    }
    public void setRotation(float rotation){
        this.rotation=rotation;
    }
    public abstract ModelType getType();
}
