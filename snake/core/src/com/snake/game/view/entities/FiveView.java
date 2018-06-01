package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

/**
 * Five view class
 */
public class FiveView extends EntityView {
    /**
     * Five view constructor
     * @param game game where view will be drawn
     */
    public FiveView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create sprite
     * @param game game where sprite will be created
     * @return create sprite
     */
    public Sprite createSprite(SnakeSmash game)
    {
        Texture texture= game.getAssetManager().get("5.png");
        return new Sprite(texture, texture.getWidth(),texture.getHeight());
    }
}
