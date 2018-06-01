package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

import javax.swing.text.html.parser.Entity;

/**
 * Green square view
 */
public class GreenSquareView extends EntityView{
    /**
     * Green square view constructor
     * @param game game where green square view will be shown
     */
    GreenSquareView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create green square view sprite
     * @param game game where sprite will be created
     * @return created sprite
     */
    @Override
    public Sprite createSprite(SnakeSmash game) {
        Texture texture = game.getAssetManager().get("greenSquare.png");
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
