package com.snake.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.snake.game.view.GameView;

public class SnakeSmash extends Game {
	SpriteBatch batch;
	private AssetManager assetManager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
	startGame();
	}

	private void startGame() {
		setScreen(new GameView(this));
	}


	public AssetManager getAssetManager() {
		return assetManager;
	}

	@Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}


}
