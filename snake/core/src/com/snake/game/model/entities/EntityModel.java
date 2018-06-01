package com.snake.game.model.entities;

public abstract class EntityModel {
    /*
    Enum with different model types
     */
    public enum ModelType {SNAKE,PINKSQUARE,COIN,BALL,YELLOWSQUARE,LIGHTPINKSQUARE,REDSQUARE,MUSTARDSQUARE,BLUESQUARE,GREENSQUARE,ZERO,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT, NINE}

    private float x;
    private float y;

    /**
     * Entity model constructor
     * @param x x model position
     * @param y y model position
     */
    public EntityModel(float x,float y){
        this.x=x;
        this.y=y;
    }

    /**
     * Function to get x model position
     * @return x model position
     */

    public float getX(){
        return x;
    }

    /**
     * Function to get y model position
     * @return y model position
     */
    public float getY(){
        return y;
    }

    /**
     * Function to set x model position
     * @param x new x model position
     */

    public void setX(float x){
        this.x=x;
    }

    /**
     * Function to set y model positiom
     * @param y new y model position
     */
    public void setY(float y){
        this.y=y;
    }

    /**
     * Function to get type of model
     * @return model type
     */
    public abstract ModelType getType();
}
