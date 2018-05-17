package com.snake.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.snake.game.model.entities.EntityModel;

public class SquareBody extends EntityBody {
    public SquareBody(World world, EntityModel model) {
        super(world, model);

        float density = 1f, friction = 0.4f, restitution = 0.5f;
        int width = 96, height = 96;

        // Fixture needs to be convex so we need two of them.
        createFixture(body, new float[]{
                0, 33, 14, 14, 46, 7, 27, 52, 7, 52
        }, width, height, density, friction, restitution, SQUARE_BODY, (short)(SQUARE_BODY|SNAKE_BODY));

    }
}
