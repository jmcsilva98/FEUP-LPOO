package com.snake.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.snake.game.controller.GameController;
import com.snake.game.tools.CollisionDetect;

import java.awt.HeadlessException;

import sun.security.pkcs11.wrapper.Constants;

public class SquareModel extends EntityModel{
    int value;
    public static final int SPEED=6;
    public static final int WIDTH =96;
    public static final int HEIGHT =96;
    public Body body;
    public boolean toRemove=false;
    public ModelType modelType;
     CollisionDetect collision;
    public SquareModel(float x, float y, float rotation, int value,ModelType model){
        super(x,y,rotation);
        this.value=value;
        this.modelType=model;
        this.collision= new CollisionDetect(x,y,WIDTH, HEIGHT);

    }
    public int getValue(){
        return value;
    }
    public void setValue(int value){
        this.value=value;
    }


    @Override
    public ModelType getType() {
        return modelType;
    }

    public void update(float delta){
        setY(getY()-SPEED*delta);
        if (getY()<0)
            toRemove=true;
       collision.move(getX(),getY());
}

   public CollisionDetect getCollisionDetect() {
        return collision;
    }

    public void setType(ModelType model){
        this.modelType=model;

    }
}
