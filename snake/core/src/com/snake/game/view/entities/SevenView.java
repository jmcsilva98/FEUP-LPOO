package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

/**
 * Seven view class
 */
public class SevenView extends EntityView {
    /**
     * Seven view constructor
     * @param game game where view will be shown
     */
    public SevenView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create seven sprite
     * @param game game where sprite will be created
     * @return created sprite
     */
    public Sprite createSprite(SnakeSmash game)
    {
        Texture texture= game.getAssetManager().get("7.png");
        return new Sprite(texture, texture.getWidth(),texture.getHeight());
    }
}
