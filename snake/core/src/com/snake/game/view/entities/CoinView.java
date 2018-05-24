package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.snake.game.SnakeSmash;
import com.snake.game.view.GameView;

public class CoinView extends EntityView {
    private final Animation<TextureRegion> animation;
    private float stateTime = 0;
    Texture texture;
    public CoinView(SnakeSmash game) {
        super(game);
        texture = game.getAssetManager().get("coin.png");
        TextureRegion[][] thrustRegion = TextureRegion.split(texture, texture.getWidth() / 6, texture.getHeight());
        // Put the frames into a uni-dimensional array
        TextureRegion[] frames = new TextureRegion[10];
        System.arraycopy(thrustRegion[0], 0, frames, 0, 10);

        animation = new Animation<TextureRegion>(.25f, frames);




    }

    public Sprite createSprite(SnakeSmash game)

    {
        //Texture texture= game.getAssetManager().get("coin.png");
        return new Sprite(texture, texture.getWidth(),texture.getHeight());
    }
}
