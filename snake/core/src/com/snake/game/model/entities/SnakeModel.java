package com.snake.game.model.entities;

/**
 * Snake model class
 */

public class SnakeModel extends EntityModel {
    private int size;
    public boolean collideWithSquare=false;

    /**
     * Snake model constructor
     * @param size size of snake
     * @param x horizontal position
     * @param y vertical position
     */
    public  SnakeModel(int size, float x, float y){
        super(x,y);
        this.size=size;
    }

    /**
     * Function to get model type
     * @return snake model type
     */

    @Override
    public ModelType getType() {
        return ModelType.SNAKE;
    }

    /**
     * Function to get size
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * Function to set size
     * @param size new snake size
     */
    public void setSize(int size){
        this.size=size;
    }

    /**
     * Function to update size
     * @param value value to add to snake size
     * @return updated size
     */
    public int updateSize(int value){
        this.size= this.size+value;
        return this.size;
    }



}
