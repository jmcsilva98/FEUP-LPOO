package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;
import com.snake.game.view.GameView;

public class CoinView extends EntityView {
    public CoinView(SnakeSmash game) {
        super(game);
    }

    public Sprite createSprite(SnakeSmash game)
    {
        Texture texture= null; //To be changed
        return new Sprite(texture, texture.getWidth(),texture.getHeight());
    }
}
