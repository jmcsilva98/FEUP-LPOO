package com.snake.game.view.entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

/**
 * pink square view class
 */
public class PinkSquareView extends EntityView{
    /**
     * Pink square view constructor
     * @param game game where view will be shown
     */
    PinkSquareView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create square sprite
     * @param game game where sprite will be created
     * @return created sprite
     */

    @Override
    public Sprite createSprite(SnakeSmash game) {
        Texture texture = game.getAssetManager().get("pinkSquare.png");
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
