package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

import java.util.Random;

/**
 * Light pink square view class
 */

public  class LightPinkSquareView extends EntityView {
    /**
     *  Light Pink Square View constructor
     * @param game game where view will be created
     */
    LightPinkSquareView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create square sprite
     * @param game game where sprite will be created
     * @return created sprite
     */
    @Override
    public Sprite createSprite(SnakeSmash game) {
        Texture texture = game.getAssetManager().get("lightpinkSquare.png");
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
