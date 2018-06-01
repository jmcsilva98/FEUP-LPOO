package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

/**
 * Blue square view class
 */
public class BlueSquareView extends EntityView {
    /**
     * Blue square view constructor
     * @param game
     */
    BlueSquareView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create blue square view sprite
     * @param game game where sprite will be created
     * @return created sprite
     */
    @Override
    public Sprite createSprite(SnakeSmash game) {

        Texture texture = game.getAssetManager().get("pinkSquare.png");
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
