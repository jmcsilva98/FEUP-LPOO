package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

public class MustardSquareView extends EntityView {
    MustardSquareView(SnakeSmash game) {
        super(game);
    }

    @Override
    public Sprite createSprite(SnakeSmash game) {
        Texture texture = game.getAssetManager().get("mustardSquare.png");
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
