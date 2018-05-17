package com.snake.game.view.menus;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.snake.game.SnakeSmash;

import com.badlogic.gdx.Screen;
import com.snake.game.view.GameView;

import static com.snake.game.controller.GameController.SCREEN_WIDTH;
import static com.snake.game.controller.GameController.SCREEN_HEIGHT;

public class SettingsMenu implements Screen {

    private static final int DEFAULT_ICON_WIDTH = 75;
    private static final int DEFAULT_ICON_HEIGHT = 75;
    private static final int BASE_WIDTH = 271;
    private static final int BASE_HEIGHT = 328;
    private static final int BASE_Y = 200;
    private static final int TITLE_WIDTH = 269;
    private static final int TITLE_HEIGHT = 70;
    private static final int TITLE_Y = 550;
    private static final int ICON_Y = 100;
    private static final int MUSIC_ICON_Y =350;


    protected final SnakeSmash game;

    private Texture exitBtn;
    private Texture title;
    private Texture musicActiveBtn;
    private Texture musicInactiveBtn;
    private Texture base;
    private Texture homeBtn;

    private boolean musicOn = true;

    public SettingsMenu(SnakeSmash game) {
        this.game = game;
        exitBtn = new Texture("exitBtn.png");
        homeBtn = new Texture("homeBtn.png");
        title = new Texture("optionTitle.png");
        musicActiveBtn = new Texture("musicActiveBtn.png");
        musicInactiveBtn = new Texture("musicInactiveBtn.png");
        base = new Texture("baseBtn.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(255 / 255f, 181 / 255f, 141 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the texture
        game.getBatch().begin();

        game.batch.draw(title, SCREEN_WIDTH / 2 - TITLE_WIDTH / 2, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
        game.getBatch().draw(base, SCREEN_WIDTH / 2 - BASE_WIDTH / 2, BASE_Y, BASE_WIDTH, BASE_HEIGHT);

        int x = SCREEN_WIDTH /2 - DEFAULT_ICON_WIDTH / 2;
        if(musicOn)
        game.getBatch().draw(musicActiveBtn, x, MUSIC_ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);
        else
        game.getBatch().draw(musicInactiveBtn, x, MUSIC_ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);

        if(Gdx.input.getX() < x + DEFAULT_ICON_WIDTH&& Gdx.input.getX()> x && SCREEN_HEIGHT - Gdx.input.getY() < MUSIC_ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - Gdx.input.getY() > MUSIC_ICON_Y){


            if(Gdx.input.isTouched()){
                //this.dispose();
                if(!musicOn){
                    //play music
                    game.getBatch().draw(musicActiveBtn, x, MUSIC_ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);
                    musicOn = true;
                }else{
                    game.getBatch().draw(musicInactiveBtn, x, MUSIC_ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);
                    musicOn = false;
                }

            }
        }

        x = 100;
        game.getBatch().draw(homeBtn, x, ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);
        if(Gdx.input.getX() < x + DEFAULT_ICON_WIDTH && Gdx.input.getX()> x && SCREEN_HEIGHT - Gdx.input.getY() < ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - Gdx.input.getY() > ICON_Y) {
            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new MainMenu(game));
            }
        }


        x += 125 + DEFAULT_ICON_WIDTH;
        game.getBatch().draw(exitBtn, x, ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);
        if(Gdx.input.getX() < x + DEFAULT_ICON_WIDTH && Gdx.input.getX()> x && SCREEN_HEIGHT - Gdx.input.getY() < ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - Gdx.input.getY() > ICON_Y) {
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }

        game.getBatch().end();
    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
