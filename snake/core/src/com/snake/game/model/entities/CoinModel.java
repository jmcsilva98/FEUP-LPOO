package com.snake.game.model.entities;

/**
 * Coin model class
 */

public class CoinModel extends EntityModel{
    int value;
    public boolean toRemove;
    private float rotation;

    /**
     * Coin model constructor
     * @param x coin model x position
     * @param y coin model y position
     * @param rotation coin model rotation
     * @param value coin model value
     */
    public CoinModel(float x, float y, float rotation, int value){
        super (x,y);
        this.value=value;
        this.rotation=rotation;
    }

    /**
     * Function to get coin value
     * @return coin value
     */
    public int getValue (){
        return value;
    }

    /**
     * Function to set coin value
     * @param value new coin value
     */
    public void setValue(int value){
        this.value=value;
    }

    /**
     * Function to get coin type
     * @return
     */
    @Override
    public ModelType getType() {
        return ModelType.COIN;
    }

    /**
     * Function to update coin position
     * @param delta system time
     * @param speed speed
     */
    public void update(float delta, float speed) {
        setY(getY()-delta*speed);
        if (getY()<0)
            toRemove=true;
    }
}
