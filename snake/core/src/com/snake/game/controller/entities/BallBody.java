package com.snake.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.snake.game.model.entities.BallModel;
import com.snake.game.model.entities.EntityModel;

public class BallBody extends EntityBody {
    public BallBody(World world , EntityModel model){
        super(world, model);
    }

}
