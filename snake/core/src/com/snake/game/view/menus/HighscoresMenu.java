package com.snake.game.view.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.snake.game.SnakeSmash;

import static com.snake.game.controller.GameController.SCREEN_HEIGHT;
import static com.snake.game.controller.GameController.SCREEN_WIDTH;

public class HighscoresMenu extends ScreenAdapter {

    private int highscore;
    protected final SnakeSmash game;

    private static final int DEFAULT_ICON_WIDTH = 75;
    private static final int DEFAULT_ICON_HEIGHT = 75;
    private static final int TITLE_WIDTH = 360;
    private static final int TITLE_HEIGHT = 51;
    private static final int TITLE_Y = 600;
    private static final int ICON_Y = 200;
    private static final int SCORE_WIDTH = 210;
    private static final int SCORE_HEIGHT = 60;
    private static final int SCORE_Y = 350;

    private BitmapFont scoreFont;

    private Texture exitBtn;
    private Texture title;
    private Texture homeBtn;
    private Texture scoreBase;

    public HighscoresMenu(final SnakeSmash game) {
        this.game = game;
        exitBtn = new Texture("exitBtn.png");
        homeBtn = new Texture("homeBtn.png");
        title = new Texture("scoresTitle.png");
        scoreBase = new Texture("scoreBase.png");

        scoreFont = game.getFont();
        Preferences prefs = Gdx.app.getPreferences("snakesmash");
        this.highscore = prefs.getInteger("highscore", 0);

        /*SaveData.loadData();
        highscores = SaveData.gameData.getHighscores();
        names = SaveData.gameData.getNames();*/

        final HighscoresMenu highscoresMenuScreen = this;

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                int x = 100;
                if (game.camera.getInputInGameWorld().x < x + DEFAULT_ICON_WIDTH && game.camera.getInputInGameWorld().x > x && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y < ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > ICON_Y) {
                    highscoresMenuScreen.dispose();
                    game.setScreen(new MainMenu(game));
                }
                x += 125 + DEFAULT_ICON_WIDTH;
                if (game.camera.getInputInGameWorld().x < x + DEFAULT_ICON_WIDTH && game.camera.getInputInGameWorld().x > x && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y < ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > ICON_Y) {
                    highscoresMenuScreen.dispose();
                    Gdx.app.exit();

                return super.touchUp(screenX, screenY, pointer, button);
                }


                return super.touchUp(screenX,screenY,pointer,button);
            }

        });
    }



    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(255 / 255f, 181 / 255f, 141 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the texture
        game.getBatch().begin();


        game.batch.draw(title, SCREEN_WIDTH / 2 - TITLE_WIDTH / 2, TITLE_Y , TITLE_WIDTH, TITLE_HEIGHT);

        int x = 100;
        game.getBatch().draw(homeBtn, x, ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);

        x += 125 + DEFAULT_ICON_WIDTH;
        game.getBatch().draw(exitBtn, x, ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);

        game.getBatch().draw(scoreBase, SCREEN_WIDTH / 2 - SCORE_WIDTH / 2, SCORE_Y, SCORE_WIDTH, SCORE_HEIGHT);

        /*String scores;
        int y = 540;
        int i;
        for( i = 0; i < highscores.length-1; i++){
            scores = String.format("%2d. %7s %s", i+1, highscores[i], names[i]);
            scoreFont.draw(game.getBatch(), scores, (SCREEN_WIDTH - 200) / 2, y);
            y -= 30;
        }

        scores = String.format("%2d. %7s %s", i+1, highscores[i], names[i]);
        scoreFont.draw(game.getBatch(), scores, (SCREEN_WIDTH - 213) / 2, y);*/
        scoreFont.setColor(Color.WHITE);
        scoreFont.draw(game.getBatch(), "Current High Score", 75, 460);

        scoreFont.setColor(Color.YELLOW);
        if(highscore >= 100)
            scoreFont.draw(game.getBatch(), ""+highscore, SCREEN_WIDTH / 2 - SCORE_WIDTH / 2 + 80, SCORE_Y + 45 );
        else
            scoreFont.draw(game.getBatch(), ""+highscore, SCREEN_WIDTH / 2 - SCORE_WIDTH / 2 + 90, SCORE_Y + 45 );

        scoreFont.setColor(Color.WHITE);



        game.getBatch().end();
    }


    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }
}
