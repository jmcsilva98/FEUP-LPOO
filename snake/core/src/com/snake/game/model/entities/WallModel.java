package com.snake.game.model.entities;



public class WallModel extends EntityModel {
    float size;
    public boolean toRemove;

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

    public void update(float delta, float speed) {
        setY(getY()-speed*delta);
        if (getY()<0)
            toRemove=true;
    }
}
