package com.snake.game.controller.entities;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.snake.game.model.entities.EntityModel;

public abstract class EntityBody {

    final Body body;
   public EntityBody(World world, EntityModel model) {
       BodyDef bodyDef = new BodyDef();
       bodyDef.type = BodyDef.BodyType.DynamicBody;
       bodyDef.position.set(model.getX(),model.getY());
       bodyDef.angle=model.getRotation();
       body = world.createBody(bodyDef);
       body.setUserData(model);

   }
   public float getX(){
       return body.getPosition().x;
   }
   public float getY() {
       return body.getPosition().y;
   }
   public float getAngle(){
       return body.getAngle();
   }
   public void setLinearVelocity(float velocity){
       body.setLinearVelocity((float) (velocity *-Math.sin(getAngle())),(float) (velocity*Math.cos(getAngle())));
   }
    public void setAngularVelocity(float omega){
       body.setAngularVelocity(omega);
    }
    public Object getUserData(){
       return body.getUserData();
    }
}
