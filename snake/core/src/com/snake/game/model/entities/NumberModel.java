package com.snake.game.model.entities;

/**
 * Number model class
 */

public class NumberModel extends EntityModel {
    int value;
    public boolean toRemove = false;
    public ModelType modelType;

    /**
     * Number model constructor
     * @param x horizontal number position
     * @param y vertical number position
     * @param value number
     * @param model number model
     */

    public NumberModel(float x, float y, int value, ModelType model) {
        super(x, y);
        this.value = value;
        this.modelType = model;


    }

    /**
     * Function to get number
     * @return number
     */
    public int getValue() {
        return value;
    }

    /**
     * Function to set number
     * @param value new number
     */

    public void setValue(int value) {
        this.value = value;
    }


    /**
     * Function to get model type
     * @return model type
     */
    @Override
    public ModelType getType() {
        return modelType;
    }

    /**
     * Function to update number position
     * @param delta system time
     * @param speed level speed
     */
    public void update(float delta, float speed) {
        setY(getY() - speed * delta);
        if (getY() < 0)
            toRemove = true;
    }
}
