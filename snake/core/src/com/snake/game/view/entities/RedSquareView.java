package com.snake.game.view.entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

/**
 * Red square view class
 */
public class RedSquareView extends EntityView {
    /**
     * Red square view constructor
     * @param game game where view will be shown
     */
    RedSquareView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create square view
     * @param game game where sprite will be created
     * @return  created sprite
     */
    @Override
    public Sprite createSprite(SnakeSmash game) {
        Texture texture = game.getAssetManager().get("redSquare.png");
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
