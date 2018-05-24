package com.snake.game.model.entities;

import com.badlogic.gdx.graphics.g3d.Model;

public class CoinModel extends EntityModel{
    int value;
    public boolean toRemove;
    public CoinModel(float x, float y, float rotation, int value){
        super (x,y,rotation);
        this.value=value;
    }
    public int getValue (){
        return value;
    }
    public void setValue(int value){
        this.value=value;
    }
    @Override
    public ModelType getType() {
        return ModelType.COIN;
    }

    public void update(float delta, float speed) {
        setY(getY()-delta*speed);
        if (getY()<0)
            toRemove=true;
    }
}
