package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.SnakeSmash;

import javax.swing.text.html.parser.Entity;

public class GreenSquareView extends EntityView{

    GreenSquareView(SnakeSmash game) {
        super(game);
    }

    @Override
    public Sprite createSprite(SnakeSmash game) {
        Texture texture = game.getAssetManager().get("greenSquare.png");
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
