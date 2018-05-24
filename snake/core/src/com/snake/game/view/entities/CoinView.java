package com.snake.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.snake.game.SnakeSmash;
import com.snake.game.view.GameView;

public class CoinView extends EntityView {
    private Animation<TextureRegion> animation;
    private float stateTime = 0;
    Texture texture;
    public CoinView(SnakeSmash game) {
        super(game);



    }
    public void updateCoin(float delta){
        stateTime += delta;
        sprite.setRegion(animation.getKeyFrame(stateTime, true));
    }

    public Sprite createSprite(SnakeSmash game)

    {
        texture = game.getAssetManager().get("coin.png");
        TextureRegion[][] thrustRegion = TextureRegion.split(texture, texture.getWidth() / 6, texture.getHeight());
        TextureRegion[] frames = new TextureRegion[6];
        System.arraycopy(thrustRegion[0], 0, frames, 0, 6);
        animation = new Animation<TextureRegion>(.25f, frames);

        return new Sprite(animation.getKeyFrame(0));
    }
}
