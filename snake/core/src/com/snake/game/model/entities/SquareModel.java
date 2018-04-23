package com.snake.game.model.entities;

public class SquareModel extends EntityModel{
    int value;
    public SquareModel(float x, float y, float rotation, int value){
        super(x,y,rotation);
        this.value=value;

    }
    public int getValue(){
        return value;
    }
    public void setValue(int value){
        this.value=value;
    }


    @Override
    public ModelType getType() {
        return ModelType.SQUARE;
    }
}
