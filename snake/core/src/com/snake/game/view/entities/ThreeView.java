package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

/**
 * Three view class
 */
public class ThreeView extends EntityView {
    /**
     * Three view constructor
     * @param game game where view will be shown
     */
    public ThreeView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create three sprite
     * @param game game where sprite will be created
     * @return created three sprite
     */
    public Sprite createSprite(SnakeSmash game)
    {
        Texture texture= game.getAssetManager().get("3.png");
        return new Sprite(texture, texture.getWidth(),texture.getHeight());
    }
}
