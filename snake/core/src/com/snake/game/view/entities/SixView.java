package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

/**
 * Six view class
 */
public class SixView extends EntityView {

    /**
     * Six view class
     * @param game game where view will be shown
     */
    public SixView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create six sprite
     * @param game game where sprite will be created
     * @return created sprite
     */
    public Sprite createSprite(SnakeSmash game)
    {
        Texture texture= game.getAssetManager().get("6.png");
        return new Sprite(texture, texture.getWidth(),texture.getHeight());
    }
}
