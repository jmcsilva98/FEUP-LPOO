package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.model.entities.EntityModel;
import com.snake.game.SnakeSmash;

import static com.snake.game.view.GameView.PIXEL_TO_METER;
public abstract class EntityView {
    Sprite sprite;

    EntityView(SnakeSmash game){
        sprite = createSprite(game);
    }

    public abstract Sprite createSprite(SnakeSmash game);

    public void update(EntityModel model){
        sprite.setCenter(model.getX()/PIXEL_TO_METER,model.getY()/PIXEL_TO_METER);
        sprite.setRotation((float) Math.toDegrees(model.getRotation()));
    }
}
