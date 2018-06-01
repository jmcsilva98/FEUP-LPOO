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

/**
 * Snake Smash class (main class)
 */

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
	private BitmapFont gameFont, gameOverFont;

	private Facebook facebook;

    /**
     * Create function
     */
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

    /**
     * Function to start game
     */
	private void startGame() {
        setScreen(new MainMenu(this));
    }

    /**
     * Function to get asset manager
     * @return
     */

	public AssetManager getAssetManager() {
		return assetManager;
	}

    /**
     * Dispose function
     */
	@Override
	public void dispose () {
		batch.dispose();
		GameController.restart();
		GameModel.restart();
		assetManager.dispose();
		gameFont.dispose();
	}

    /**
     * Render function
     */
	@Override
	public void render () {
		batch.setProjectionMatrix(camera.combined());
		super.render();

	}

    /**
     * Resize function
     * @param width width
     * @param height height
     */
	@Override
	public void resize(int width, int height) {
		camera.update(width, height);
		this.scrollingBackground.resize(width);
		super.resize(width, height);
	}

    /**
     * Function to get sprite batch
     * @return sprite batch
     */

	public SpriteBatch getBatch() {
		return batch;
	}

    /**
     * Function to get if music is on
     * @return true if music is on, otherwise false
     */

	public boolean getMusic() {
		return music;
	}
    /**
     * Function to get if vibration is on
     * @return true if vibration is on, otherwise false
     */

	public boolean getVibration() {
		return vibration;
	}

    /**
     * Function to set music on or off
     * @param music boolean to set music on or off
     */

	public void setMusic(boolean music) {
		this.music = music;
	}

    /**
     * Function to init font
     */
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

    /**
     * Function to init game over font
     */
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

    /**
     * Function to get font
     * @return font
     */
	public BitmapFont getFont() {
		return gameFont;
	}

    /**
     * Function to get game over font
     * @return font
     */
	public BitmapFont getGameOverFont() {return gameOverFont;}

    /**
     * Function to get facebook
     * @return facebook
     */
	public Facebook getFacebook() {
		return facebook;
	}

    /**
     * Function to set vibration on or off
     * @param vibration boolean true if music turns on, false otherwise 
     */

	public void setVibration(boolean vibration) {
		this.vibration =vibration;
	}
}
