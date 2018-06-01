package com.snake.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;

/**
 * Square model class
 *
 */
public class SquareModel extends EntityModel{
    int value;
    public static final int WIDTH =96;
    public static final int HEIGHT =96;
    public float time_to_decrement=1f;
    public ArrayList<NumberModel>numbers=new ArrayList<NumberModel>();
    public Body body;
    public boolean toRemove=false;
    public ModelType modelType;

    /**
     * Square model constructor
     * @param x horizontal position
     * @param y vertical position
     * @param value square value
     * @param model square model
     */
    public SquareModel(float x, float y, int value, ModelType model){
        super(x,y);
        this.value=value;
        this.modelType=model;


    }

    /**
     * Function to get value
     * @return value
     */
    public int getValue(){
        return value;
    }

    /**
     * Function to set value
     * @param value new value
     */
    public void setValue(int value){
        this.value=value;
    }

    /**
     * Function to get square model type
     * @return square model tpe
     */
    @Override
    public ModelType getType() {
        return modelType;
    }

    /**
     * Function to update square position
     * @param delta system time
     * @param speed level speed
     */

    public void update(float delta,float speed){
        setY(getY()-speed*delta);
        if (getY()<0)
            toRemove=true;

}


    /**
     * Function to set model type
     * @param model model to be setted
     */
    public void setType(ModelType model){
        this.modelType=model;

    }
}
