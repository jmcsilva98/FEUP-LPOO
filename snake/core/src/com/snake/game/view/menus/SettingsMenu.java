package com.snake.game.view.menus;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.snake.game.SnakeSmash;
import com.snake.game.view.GameView;
import com.badlogic.gdx.Screen;


import java.util.Set;

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


    public SettingsMenu(final SnakeSmash game) {
        this.game = game;
        exitBtn = new Texture("exitBtn.png");
        homeBtn = new Texture("homeBtn.png");
        title = new Texture("optionTitle.png");
        musicActiveBtn = new Texture("musicActiveBtn.png");
        musicInactiveBtn = new Texture("musicInactiveBtn.png");
        base = new Texture("baseBtn.png");

        final SettingsMenu settingsMenuScreen = this;

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                int x = SCREEN_WIDTH /2 - DEFAULT_ICON_WIDTH / 2;
                if(game.camera.getInputInGameWorld().x < x + DEFAULT_ICON_WIDTH &&game.camera.getInputInGameWorld().x> x && SCREEN_HEIGHT -game.camera.getInputInGameWorld().y < MUSIC_ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > MUSIC_ICON_Y) {

                    if (!game.getMusic()) {
                        game.setMusic(true);

                    } else {
                        game.setMusic(false);

                    }

                }
                x = 100;
                if(game.camera.getInputInGameWorld().x < x + DEFAULT_ICON_WIDTH && game.camera.getInputInGameWorld().x> x && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y < ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > ICON_Y) {
                        settingsMenuScreen.dispose();
                        game.setScreen(new MainMenu(game));
                }

                x += 125 + DEFAULT_ICON_WIDTH;
                if(game.camera.getInputInGameWorld().x < x + DEFAULT_ICON_WIDTH && game.camera.getInputInGameWorld().x > x && SCREEN_HEIGHT -game.camera.getInputInGameWorld().y < ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > ICON_Y) {
                        settingsMenuScreen.dispose();
                        Gdx.app.exit();

                }

                return super.touchUp(screenX, screenY, pointer, button);
            }

        });
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
        if(game.getMusic())
        game.getBatch().draw(musicActiveBtn, x, MUSIC_ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);
        else
        game.getBatch().draw(musicInactiveBtn, x, MUSIC_ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);

       if(game.camera.getInputInGameWorld().x < x + DEFAULT_ICON_WIDTH && game.camera.getInputInGameWorld().x > x && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y < MUSIC_ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > MUSIC_ICON_Y){

            if(Gdx.input.isTouched()){
                //this.dispose();
                if(!game.getMusic()){
                    //play music
                    game.getBatch().draw(musicActiveBtn, x, MUSIC_ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);
                   // game.setMusic(true);
                }else{
                    game.getBatch().draw(musicInactiveBtn, x, MUSIC_ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);
                  //  game.setMusic(false);
                }

            }
        }

        x = 100;
        game.getBatch().draw(homeBtn, x, ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);

        x += 125 + DEFAULT_ICON_WIDTH;
        game.getBatch().draw(exitBtn, x, ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);

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
        Gdx.input.setInputProcessor(null);
    }
}
