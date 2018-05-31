package com.snake.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.snake.game.controller.GameController;
import com.snake.game.model.GameModel;
import com.snake.game.tools.Facebook;
import com.snake.game.tools.GameCamera;
import com.snake.game.tools.ScrollingBackground;
import com.snake.game.view.menus.MainMenu;



public class SnakeSmash extends Game {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;
	public static boolean IS_MOBILE = false;
	public SpriteBatch batch;
	private Sound coinSound;
	private AssetManager assetManager;
	public ScrollingBackground scrollingBackground;
	public GameCamera camera;
	private boolean music = true;
	private boolean vibrate =true;
	private BitmapFont bitmapfont;

	private Facebook facebook;


	@Override
	public void create () {
		batch = new SpriteBatch();
		this.scrollingBackground=new ScrollingBackground();
		camera = new GameCamera(WIDTH, HEIGHT);
		assetManager = new AssetManager();
		if (Gdx.app.getType()== ApplicationType.Android || Gdx.app.getType()==ApplicationType.iOS)
            IS_MOBILE = true;

		facebook = new Facebook();
		//Gdx.input.setInputProcessor(new GameInputProcessor());

		initFont();


	startGame();
	}

	private void startGame() {
		setScreen(new MainMenu(this));
		//setScreen(new GameView(this,9));
	}


	public AssetManager getAssetManager() {
		return assetManager;
	}

	@Override
	public void dispose () {
		batch.dispose();
		GameController.restart();
		GameModel.restart();
		assetManager.dispose();
		bitmapfont.dispose();
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


	public boolean getMusic() {
		return music;
	}

	public Sound getCoinSound() {
		return coinSound;
	}

	public boolean getVibrate() {
		return vibrate;
	}

	public void setMusic(boolean music) {
		this.music = music;
	}

	public void initFont(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/HVD_Comic_Serif_Pro.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parameter.size = 30;
		parameter.color = Color.WHITE;
		parameter.borderWidth = 1;
		parameter.borderColor = Color.BLACK;
		bitmapfont = generator.generateFont(parameter); // font size 12

		generator.dispose(); // don't forget to dispose to avoid memory leaks!

	}

	public BitmapFont getFont() {
		return bitmapfont;
	}

	public Facebook getFacebook() {
		return facebook;
	}
}
