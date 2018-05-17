package com.snake.game.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.snake.game.model.entities.EntityModel;

public class SnakeBody extends EntityBody {
    public SnakeBody(World world, EntityModel model) {
        super(world, model);


    }
}


