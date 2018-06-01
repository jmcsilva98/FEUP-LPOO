package com.snake.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
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
	private boolean vibration =true;
	private BitmapFont bitmapfont;
	private BitmapFont gameFont, gameOverFont;

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
		initGameOverFont();


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
		gameFont.dispose();
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

	public boolean getVibration() {
		return vibration;
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
		gameFont = generator.generateFont(parameter); // font size 12

		generator.dispose(); // don't forget to dispose to avoid memory leaks!

	}
	public void initGameOverFont(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/HVD_Comic_Serif_Pro.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parameter.size = 15;
		parameter.color = Color.ORANGE;
		parameter.borderWidth = 1;
		parameter.borderColor = Color.BLACK;
		gameOverFont = generator.generateFont(parameter); // font size 12

		generator.dispose(); // don't forget to dispose to avoid memory leaks!
	}
	public BitmapFont getFont() {
		return gameFont;
	}
	public BitmapFont getGameOverFont() {return gameOverFont;}

	public Facebook getFacebook() {
		return facebook;
	}

	public void setVibration(boolean b) {
		this.vibration =b;
	}
}
