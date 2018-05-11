package com.snake.game.model.entities;

import com.badlogic.gdx.Gdx;
import com.snake.game.tools.CollisionDetect;


public class SquareModel extends EntityModel{
    int value;
    public static final int SPEED=6;
    public static final int WIDTH =16;
    public static final int HEIGHT =16;
    public boolean toRemove=false;

    CollisionDetect collision;
    public SquareModel(float x, float y, float rotation, int value){
        super(x,y,rotation);
        this.collision= new CollisionDetect(x,y,WIDTH,HEIGHT);
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

    public void update(float delta){
        setY(getY()-SPEED*delta);
        if (getY()<2)
            toRemove=true;
        collision.move(getX(),getY());
    }

    public CollisionDetect getCollisionDetect() {
        return collision;
    }
}
