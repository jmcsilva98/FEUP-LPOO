package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

/**
 * Mustard square view
 */
public class MustardSquareView extends EntityView {
    /**
     * Mustard square view
     * @param game game where sprite will be created
     */
    MustardSquareView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create square sprite
     * @param game game where sprite will be created
     * @return created sprite
     */

    @Override
    public Sprite createSprite(SnakeSmash game) {
        Texture texture = game.getAssetManager().get("mustardSquare.png");
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
