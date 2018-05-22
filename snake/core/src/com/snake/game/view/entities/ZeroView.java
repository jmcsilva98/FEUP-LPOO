package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

public class ZeroView extends EntityView {

    public ZeroView(SnakeSmash game) {
        super(game);
    }
    public Sprite createSprite(SnakeSmash game)
    {
        Texture texture= game.getAssetManager().get("0.png");
        return new Sprite(texture, texture.getWidth(),texture.getHeight());
    }
}
