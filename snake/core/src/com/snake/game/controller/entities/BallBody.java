package com.snake.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.snake.game.model.entities.BallModel;
import com.snake.game.model.entities.EntityModel;

public class BallBody extends EntityBody {
    public BallBody(World world, EntityModel model) {
        super(world, model);
        float density = 1f, friction = 0.4f, restitution = 0.5f;
        int width = 17, height = 17;

        createFixture(body, new float[]{
                5, 5, 5, 10, 10, 10, 10, 5,
        }, width, height, density, friction, restitution, BALL_BODY, (short) (SQUARE_BODY|SNAKE_BODY));
    }
}
