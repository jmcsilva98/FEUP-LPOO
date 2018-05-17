package com.snake.game.model.entities;


public class SnakeModel extends EntityModel {
    public static final int WIDTH =17;
    public static final int HEIGHT =17;
    private int size;

    public  SnakeModel(int size, float x, float y,float rotation){
        super(x,y,rotation);
        this.size=size;

    }

    @Override
    public ModelType getType() {
        return ModelType.SNAKE;
    }
    public int getSize(){
        return size;
    }
    public void setSize(int size){
        this.size=size;
    }

    public int updateSize(int value){
        this.size= this.size-value;

        return this.size;
    }

    public void addBalls(int value){
        this.size+=value;
    }

}
