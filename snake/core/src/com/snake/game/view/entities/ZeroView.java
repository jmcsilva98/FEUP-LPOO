package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

/**
 * Zero view class
 */
public class ZeroView extends EntityView {
    /**
     * Zero view constructor
     * @param game game where view will be shown
     */
    public ZeroView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create zero sprite
     * @param game game where sprite will be created
     * @return created zero view sprite
     */
    public Sprite createSprite(SnakeSmash game)
    {
        Texture texture= game.getAssetManager().get("0.png");
        return new Sprite(texture, texture.getWidth(),texture.getHeight());
    }
}
