package com.snake.game.model.entities;

/**
 * Ball model class
 */
public class BallModel extends EntityModel {
    int value ;
    public boolean toRemove =false;

    /**
     * Ball model constructor
     * @param x x ball position
     * @param y y ball position
     * @param value
     */
    public BallModel(float x, float y, int value) {
        super(x, y);
        this.value =value;

    }

    /**
     * Function to get ball value
     * @return ball value
     */
    public int getValue(){
        return value;
    }

    /**
     * Function to set ball value
     * @param value new ball value
     */
    public void setValue(int value){
        this.value =value;
    }

    /**
     * Function to update ball position
     * @param delta system time
     * @param speed speed
     */

    public void update(float delta,float speed){
        setY(getY()-speed*delta);
        if (getY()<0)
            toRemove=true;

    }


    @Override
    public ModelType getType() {
        return ModelType.BALL;
    }
}
