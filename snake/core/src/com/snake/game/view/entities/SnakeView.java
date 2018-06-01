package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;
import com.snake.game.view.GameView;

/**
 * Snake view class
 */
public class SnakeView extends EntityView {
    /**
     * Snake view constructor
     * @param game game where view will be shown
     */
    public SnakeView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create snake sprite
     * @param game game where sprite will be created
     * @return created snake sprite
     */
    public Sprite createSprite(SnakeSmash game)
    {
        Texture texture=game.getAssetManager().get("whiteBall.png");
        return new Sprite(texture, texture.getWidth(),texture.getHeight());
    }
}
