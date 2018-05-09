package com.snake.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.snake.game.tools.GameCamera;
import com.snake.game.tools.ScrollingBackground;
import com.snake.game.view.GameView;
import com.snake.game.view.menus.MainMenu;


public class SnakeSmash extends Game {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;
	public static boolean IS_MOBILE = false;
	public SpriteBatch batch;
	private AssetManager assetManager;
	public ScrollingBackground scrollingBackground;
	public GameCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.scrollingBackground=new ScrollingBackground();
		camera = new GameCamera(WIDTH, HEIGHT);
		assetManager = new AssetManager();
	startGame();
	}

	private void startGame() {
		setScreen(new MainMenu(this));
	}


	public AssetManager getAssetManager() {
		return assetManager;
	}

	@Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
	}
	@Override
	public void render () {
		batch.setProjectionMatrix(camera.combined());
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		camera.update(width, height);
		this.scrollingBackground.resize(width,height);
		super.resize(width, height);
	}

	public SpriteBatch getBatch() {
		return batch;
	}


}
