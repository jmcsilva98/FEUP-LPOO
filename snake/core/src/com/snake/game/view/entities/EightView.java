package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

/**
 * Eight view class
 */
public class EightView extends EntityView {
    /**
     * Eight view constructor
     * @param game game where view will be shown
     */
    public EightView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create sprite
     * @param game game where sprite will be created
     * @return created sprite
     */
    public Sprite createSprite(SnakeSmash game)
    {
        Texture texture= game.getAssetManager().get("8.png");
        return new Sprite(texture, texture.getWidth(),texture.getHeight());
    }
}
