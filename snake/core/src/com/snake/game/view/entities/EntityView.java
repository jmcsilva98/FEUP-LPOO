package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.snake.game.model.entities.EntityModel;
import com.snake.game.SnakeSmash;

import static com.snake.game.view.GameView.PIXEL_TO_SQUARE;

/**
 * Entity view class
 */
public abstract class EntityView {
    Sprite sprite;

    /**
     * Entity view constructor
     * @param game game where view will be created
     */
    EntityView(SnakeSmash game){
        sprite = createSprite(game);
    }

    /**
     * Function to create sprite
     * @param game game where sprite will be created
     * @return created sprite
     */

    public abstract Sprite createSprite(SnakeSmash game);

    public void update(EntityModel model){
        sprite.setCenter(model.getX()/ PIXEL_TO_SQUARE,
                model.getY()/ PIXEL_TO_SQUARE);
    }

    public  void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

}
