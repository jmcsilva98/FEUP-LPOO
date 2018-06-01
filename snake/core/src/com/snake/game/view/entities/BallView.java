package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

/**
 * Ball view class
 */
public class BallView extends EntityView {
    /**
     * Ball view constructor
     * @param game game to draw ball
     */
    public BallView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create sprite
     * @param game game where ball sprite will be created
     * @return created ball sprite
     */
    public Sprite createSprite(SnakeSmash game)
    {
        Texture texture= game.getAssetManager().get("whiteBall.png");
        return new Sprite(texture, texture.getWidth(),texture.getHeight());
    }
}
