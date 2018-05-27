package com.snake.game.view.menus;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.snake.game.SnakeSmash;
import com.snake.game.view.GameView;

import static com.snake.game.controller.GameController.SCREEN_HEIGHT;
import static com.snake.game.controller.GameController.SCREEN_WIDTH;

//import sun.applet.Main;

public class MainMenu implements Screen {
    private static final int DEFAULT_ICON_WIDTH = 75;
    private static final int DEFAULT_ICON_HEIGHT = 75;
    private static final int DEFAULT_PLAY_WIDTH = 175;
    private static final int DEFAULT_PLAY_HEIGHT = 92;
    private static final int TITLE_WIDTH = 372;
    private static final int TITLE_HEIGHT = 189;
    private static final int TITLE_Y = 500;
    private static final int BEGINNER_Y = 400;
    private static final int INTERMEDIATE_Y = 300;
    private static final int ICON_Y = 100;
    private static final int IMPOSSIBLE_Y = 200;

    protected final SnakeSmash game;

    private Texture exitBtn;
    private Texture settingsBtn;
    private Texture beginnerActiveBtn;
    private Texture beginnerInactiveBtn;
    private Texture intermediateInactiveBtn;
    private Texture intermediateActiveBtn;
    private Texture impossibleInactiveBtn;
    private Texture impossibleActiveBtn;
    private Texture facebookBtn;
    private Texture scoresBtn;
    private Texture title;


    public MainMenu(final SnakeSmash game){

        this.game = game;
        exitBtn = new Texture("exitBtn.png");
        settingsBtn = new Texture("settingsBtn.png");
        beginnerActiveBtn = new Texture("BeginnerActive.png");
        beginnerInactiveBtn = new Texture("beginnerInactive.png");
        intermediateActiveBtn = new Texture("intermediateActive.png");
        intermediateInactiveBtn = new Texture("intermediateInactive.png");
        impossibleActiveBtn = new Texture("impossibleActive.png");
        impossibleInactiveBtn = new Texture("impossibleInactive.png");
        facebookBtn = new Texture("shareBtn.png");
        scoresBtn = new Texture("scoresBtn.png");
        title = new Texture("title.png");

        final MainMenu mainMenuScreen = this;

        Gdx.input.setInputProcessor(new InputAdapter(){

            @Override
             public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                //Goes to the game view
                int x = SCREEN_WIDTH / 2 - DEFAULT_PLAY_WIDTH / 2;
                if (game.camera.getInputInGameWorld().x < x + DEFAULT_PLAY_WIDTH && game.camera.getInputInGameWorld().x > x && SCREEN_HEIGHT -  game.camera.getInputInGameWorld().y < BEGINNER_Y + DEFAULT_PLAY_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > BEGINNER_Y) {
                    mainMenuScreen.dispose();
                    game.setScreen(new GameView(game, 6));
                }
                if (game.camera.getInputInGameWorld().x < x + DEFAULT_PLAY_WIDTH && game.camera.getInputInGameWorld().x > x && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y < INTERMEDIATE_Y + DEFAULT_PLAY_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > INTERMEDIATE_Y) {
                    mainMenuScreen.dispose();
                    //Go to intermediate game view
                    game.setScreen(new GameView(game, 12));

                }
                if (game.camera.getInputInGameWorld().x < x + DEFAULT_PLAY_WIDTH && game.camera.getInputInGameWorld().x > x && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y < IMPOSSIBLE_Y + DEFAULT_PLAY_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > IMPOSSIBLE_Y) {
                    mainMenuScreen.dispose();
                    game.setScreen(new GameView(game, 16));
                    //go to impossible game view

                }
                x = SCREEN_WIDTH - DEFAULT_ICON_WIDTH - 50;
                if (Gdx.input.getX() < x + DEFAULT_ICON_WIDTH && Gdx.input.getX() > x && SCREEN_HEIGHT - Gdx.input.getY() < ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - Gdx.input.getY() > ICON_Y) {
                    mainMenuScreen.dispose();
                    Gdx.app.exit();
                }
                x = 50;
                if (game.camera.getInputInGameWorld().x < x + DEFAULT_ICON_WIDTH &&game.camera.getInputInGameWorld().x > x && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y < ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > ICON_Y) {
                    mainMenuScreen.dispose();
                    //go to settings menu
                    game.setScreen(new SettingsMenu(game));

                }

                x += 25 + DEFAULT_ICON_WIDTH;
                if (game.camera.getInputInGameWorld().x < x + DEFAULT_ICON_WIDTH && game.camera.getInputInGameWorld().x > x && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y < ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > ICON_Y) {
                    mainMenuScreen.dispose();
                    //go to scores menu
                    game.setScreen(new HighscoresMenu(game));
                }

                x += 25 + DEFAULT_ICON_WIDTH;
                if (game.camera.getInputInGameWorld().x < x + DEFAULT_ICON_WIDTH && game.camera.getInputInGameWorld().x > x && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y < ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > ICON_Y) {
                    mainMenuScreen.dispose();
                    //go to share menu


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
        Gdx.gl.glClearColor(255/255f, 181/255f, 141/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the texture
        game.getBatch().begin();

        int x = SCREEN_WIDTH /2 - DEFAULT_PLAY_WIDTH / 2;
        if(game.camera.getInputInGameWorld().x < x + DEFAULT_PLAY_WIDTH && game.camera.getInputInGameWorld().x> x && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y < BEGINNER_Y + DEFAULT_PLAY_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > BEGINNER_Y){
            game.getBatch().draw(beginnerActiveBtn, x, BEGINNER_Y, DEFAULT_PLAY_WIDTH, DEFAULT_PLAY_HEIGHT);

        }else{
            game.getBatch().draw(beginnerInactiveBtn, x, BEGINNER_Y, DEFAULT_PLAY_WIDTH, DEFAULT_PLAY_HEIGHT);
        }

        if(game.camera.getInputInGameWorld().x < x + DEFAULT_PLAY_WIDTH && game.camera.getInputInGameWorld().x> x && SCREEN_HEIGHT -game.camera.getInputInGameWorld().y < INTERMEDIATE_Y + DEFAULT_PLAY_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > INTERMEDIATE_Y){
            game.getBatch().draw(intermediateActiveBtn, x, INTERMEDIATE_Y, DEFAULT_PLAY_WIDTH, DEFAULT_PLAY_HEIGHT);

        }else{
            game.getBatch().draw(intermediateInactiveBtn, x, INTERMEDIATE_Y, DEFAULT_PLAY_WIDTH, DEFAULT_PLAY_HEIGHT);
        }

        if(game.camera.getInputInGameWorld().x < x + DEFAULT_PLAY_WIDTH && game.camera.getInputInGameWorld().x> x && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y < IMPOSSIBLE_Y + DEFAULT_PLAY_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > IMPOSSIBLE_Y){
            game.getBatch().draw(impossibleActiveBtn, x, IMPOSSIBLE_Y, DEFAULT_PLAY_WIDTH, DEFAULT_PLAY_HEIGHT);

        }else{
            game.getBatch().draw(impossibleInactiveBtn, x, IMPOSSIBLE_Y, DEFAULT_PLAY_WIDTH, DEFAULT_PLAY_HEIGHT);
        }

        x = SCREEN_WIDTH  - DEFAULT_ICON_WIDTH - 50;
        game.getBatch().draw(exitBtn, x, ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);

        x = 50;
        game.getBatch().draw(settingsBtn, x, ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);


        x += 25 + DEFAULT_ICON_WIDTH;
        game.getBatch().draw(scoresBtn, x, ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);

        x += 25 + DEFAULT_ICON_WIDTH;
        game.getBatch().draw(facebookBtn, x, ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);

        game.batch.draw(title, SCREEN_WIDTH / 2 - TITLE_WIDTH / 2, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);

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
