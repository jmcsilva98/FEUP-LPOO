package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

/**
 * Four view class
 */

public class FourView extends EntityView {
    /**
     * Four view constructor
     * @param game game where view will be shown
     */
    public FourView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create four view sprite
     * @param game game where sprite will be created
     * @return created sprite
     */
    public Sprite createSprite(SnakeSmash game)
    {
        Texture texture= game.getAssetManager().get("4.png");
        return new Sprite(texture, texture.getWidth(),texture.getHeight());
    }
}
