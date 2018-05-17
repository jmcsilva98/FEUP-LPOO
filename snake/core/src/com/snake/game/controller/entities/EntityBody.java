package com.snake.game.controller.entities;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.snake.game.model.entities.EntityModel;

import static com.snake.game.view.GameView.PIXEL_TO_SQUARE;

public abstract class EntityBody {
    final static short BALL_BODY = 0x0001;
    final static short COIN_BODY = 0x0002;
    final static short SNAKE_BODY = 0x0004;
    final static short SQUARE_BODY = 0x0006;


    final Body body;
   public EntityBody(World world, EntityModel model) {
       BodyDef bodyDef = new BodyDef();
       bodyDef.type = BodyDef.BodyType.DynamicBody;
       bodyDef.position.set((float)model.getX(),(float)model.getY());
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
    final void createFixture(Body body, float[] vertexes, int width, int height, float density, float friction, float restitution, short category, short mask) {
        // Transform pixels into meters, center and invert the y-coordinate
        for (int i = 0; i < vertexes.length; i++) {
            if (i % 2 == 0) vertexes[i] -= width / 2;   // center the vertex x-coordinate
            if (i % 2 != 0) vertexes[i] -= height / 2;  // center the vertex y-coordinate

            if (i % 2 != 0) vertexes[i] *= -1;          // invert the y-coordinate

            vertexes[i] *= PIXEL_TO_SQUARE;              // scale from pixel to meter
        }

        PolygonShape polygon = new PolygonShape();
        polygon.set(vertexes);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixtureDef.filter.categoryBits = category;
        fixtureDef.filter.maskBits = mask;

        body.createFixture(fixtureDef);

        polygon.dispose();
    }
}
