package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

/**
 * Two view class
 */
public class TwoView extends EntityView {
    /**
     * Two view constructor
     * @param game game where view will be shown
     */
    public TwoView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create sprite
     * @param game game where sprite will be created
     * @return created sprite
     */
    public Sprite createSprite(SnakeSmash game)
    {
        Texture texture= game.getAssetManager().get("2.png");
        return new Sprite(texture, texture.getWidth(),texture.getHeight());
    }
}
