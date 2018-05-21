package com.snake.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.snake.game.model.entities.EntityModel;

public class SquareBody extends EntityBody {
    public SquareBody(World world, EntityModel model) {
        super(world, model);
    }
}
