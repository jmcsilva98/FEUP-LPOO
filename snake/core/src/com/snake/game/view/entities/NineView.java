package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

/**
 * Nine view class
 */

public class NineView extends EntityView {

    /**
     * Nine view constructor
     * @param game game where sprite will be created
     */
    public NineView(SnakeSmash game) {
        super(game);
    }

    /**
     * Function to create number sprite
     * @param game game where sprite will be created
     * @return created sprite
     */
    public Sprite createSprite(SnakeSmash game)
    {
        Texture texture= game.getAssetManager().get("9.png");
        return new Sprite(texture, texture.getWidth(),texture.getHeight());
    }
}
