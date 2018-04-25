package com.snake.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.snake.game.model.entities.BallModel;

public class BallBody extends EntityBody {
    public BallBody(World world , BallModel model){
        super(world, model);
    }

}
