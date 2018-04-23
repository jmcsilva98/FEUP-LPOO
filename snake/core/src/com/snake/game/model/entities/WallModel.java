package com.snake.game.model.entities;



public class WallModel extends EntityModel {
    float size;

    public WallModel(float x, float y, float rotation,float size) {
        super(x, y, rotation);
        this.size = size;
    }
    public float getWall(){
        return size;
    }
    public void setSize(float size){
        this.size=size;
    }

    @Override
    public ModelType getType() {
        return ModelType.WALL;
    }
}
