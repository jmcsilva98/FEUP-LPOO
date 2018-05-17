package com.snake.game.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.snake.game.model.entities.EntityModel;

public class SnakeBody extends EntityBody {
    public SnakeBody(World world, EntityModel model) {
        super(world, model);

        float density = 1f, friction = 0.4f, restitution = 0.5f;
        int width = 17, height = 17;

        // Fixture needs to be convex so we need two of them.
        createFixture(body, new float[]{
                0, 33, 14, 14, 46, 7, 27, 52, 7, 52
        }, width, height, density, friction, restitution, SNAKE_BODY, (short) (BALL_BODY|SQUARE_BODY));

    }
}


