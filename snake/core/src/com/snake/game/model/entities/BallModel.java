package com.snake.game.model.entities;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class BallModel extends EntityModel {
    int value ;
    boolean wasCatched =false;
    public BallModel(float x, float y, float rotation, int value) {
        super(x, y, rotation);
        this.value =value;

    }

    public int getValue(){
        return value;
    }
    public void setValue(int value){
        this.value =value;
    }
    public boolean getCatched(){
        return wasCatched;
    }
    public void setCatched(){
        this.wasCatched=true;
    }


    @Override
    public ModelType getType() {
        return ModelType.BALL;
    }
}
