package com.snake.game.view.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.snake.game.SnakeSmash;
import com.snake.game.controller.GameController;
import com.snake.game.model.GameModel;
import com.snake.game.view.GameView;

import static com.snake.game.controller.GameController.SCREEN_HEIGHT;
import static com.snake.game.controller.GameController.SCREEN_WIDTH;

public class GameOverMenu implements Screen {

    private static final int DEFAULT_ICON_WIDTH = 75;
    private static final int DEFAULT_ICON_HEIGHT = 75;
    private static final int PLAY_WIDTH = 125;
    private static final int PLAY_HEIGHT = 125;
    private static final int TITLE_WIDTH = 353;
    private static final int TITLE_HEIGHT = 45;
    private static final int TITLE_Y = 550;
    private static final int ICON_Y = 200;
    private static final int PLAY_Y = 100;
    private static final int SCORE_WIDTH = 210;
    private static final int SCORE_HEIGHT = 60;
    private static final int SCORE_Y = 350;
    private static final int CROWN_WIDTH = 100;
    private static final int CROWN_HEIGHT = 100;
    private static final int CROWN_Y = 425;

    protected final SnakeSmash game;

    private Texture exitBtn;
    private Texture title;
    private Texture playAgainBtn;
    private Texture scoresBase;
    private Texture crown;
    private Texture homeBtn;
    int score, highscore;
    BitmapFont scoreFont;

    public GameOverMenu(final SnakeSmash game, int score) {
        this.game = game;
        this.score = score;

        //gets highscore from the save file
        Preferences prefs = Gdx.app.getPreferences("snakesmash");
        this.highscore = prefs.getInteger("highscore", 0);

        //check if score beats highscore
        if(score > highscore){
            prefs.putInteger("highscore", score);
            prefs.flush(); //saves the file
        }

        scoreFont = game.getFont();
        exitBtn = new Texture("exitBtn.png");
        homeBtn = new Texture("homeBtn.png");
        title = new Texture("gameOverTitle.png");
        playAgainBtn = new Texture("playAgainBtn.png");
        scoresBase = new Texture("scoreBase.png");
        crown = new Texture("crown.png");


        final GameOverMenu gameOverMenuScreen = this;

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                int x = SCREEN_WIDTH / 2 - PLAY_WIDTH / 2;
                if (game.camera.getInputInGameWorld().x < x + PLAY_WIDTH && game.camera.getInputInGameWorld().x > x && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y < PLAY_Y + PLAY_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > PLAY_Y) {
                        gameOverMenuScreen.dispose();
                         GameModel.restart();
                         GameController.restart();
                        game.setScreen(new GameView(game, 6));

                }
                x = 125;
                if (game.camera.getInputInGameWorld().x < x + DEFAULT_ICON_WIDTH && game.camera.getInputInGameWorld().x > x && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y < ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > ICON_Y) {
                        gameOverMenuScreen.dispose();
                        GameModel.restart();
                        GameController.restart();
                        game.setScreen(new MainMenu(game));
                }
                x += 75 + DEFAULT_ICON_WIDTH;
                if (game.camera.getInputInGameWorld().x < x + DEFAULT_ICON_WIDTH && game.camera.getInputInGameWorld().x > x && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y < ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > ICON_Y) {
                        gameOverMenuScreen.dispose();
                        Gdx.app.exit();

                }


                return super.touchUp(screenX,screenY,pointer,button);
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
        game.getBatch().draw(crown, SCREEN_WIDTH / 2 - CROWN_WIDTH / 2, CROWN_Y, CROWN_WIDTH, CROWN_HEIGHT);
        game.getBatch().draw(scoresBase, SCREEN_WIDTH / 2 - SCORE_WIDTH / 2, SCORE_Y, SCORE_WIDTH, SCORE_HEIGHT);

        int x = SCREEN_WIDTH / 2 - PLAY_WIDTH / 2;
        game.getBatch().draw(playAgainBtn, x, PLAY_Y,PLAY_WIDTH, PLAY_HEIGHT);
       /* if (game.camera.getInputInGameWorld().x < x + PLAY_WIDTH && game.camera.getInputInGameWorld().x > x && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y < PLAY_Y + PLAY_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > PLAY_Y)
            {
                if(Gdx.input.isTouched()) {
                    this.dispose();
                    //game.getBatch().end();
                    game.setScreen(new GameView(game, 6));
                }
            }*/



        x = 125;
        game.getBatch().draw(homeBtn, x, ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);

        x += 75 + DEFAULT_ICON_WIDTH;
        game.getBatch().draw(exitBtn, x, ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);


        scoreFont.draw(game.getBatch(), ""+score, SCREEN_WIDTH / 2 - SCORE_WIDTH / 2 + 90, SCORE_Y + 45 );
        //scoreFont.draw(game.getBatch(), ""+highscore, SCREEN_WIDTH / 2 - SCORE_WIDTH / 2 + 90, SCORE_Y - 50 );
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