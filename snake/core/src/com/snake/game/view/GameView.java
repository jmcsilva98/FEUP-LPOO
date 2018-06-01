package com.snake.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.snake.game.Position;
import com.snake.game.SnakeSmash;
import com.snake.game.controller.GameController;
import com.snake.game.model.GameModel;
import com.snake.game.model.entities.BallModel;
import com.snake.game.model.entities.CoinModel;
import com.snake.game.model.entities.EntityModel;
import com.snake.game.model.entities.NumberModel;
import com.snake.game.model.entities.SquareModel;
import com.snake.game.view.entities.CoinView;
import com.snake.game.view.entities.EntityView;
import com.snake.game.view.entities.ViewFactory;
import com.snake.game.view.menus.GameOverMenu;


import java.util.ArrayList;
import java.util.Random;

import static com.snake.game.controller.GameController.SCREEN_HEIGHT;


public class GameView extends ScreenAdapter {
    public final static float PIXEL_TO_SQUARE = 0.04f;
    private final SnakeSmash game;
    public ArrayList<Position> positions;
    private Music music;
    public Sound coinSound;
    private float squareSpawnTimer;
    private float ballSpawnTimer;
    private float coinSpawnTimer;
    private static final float MIN_SQUARE_SPAWN_TIME = 0.6f;
    private static final float MAX_SQUARE_SPAWN_TIME = 4f;
    private static final float MIN_BALL_SPAWN_TIME = 1f;
    private static final float MAX_BALL_SPAWN_TIME = 3f;
    private static final float MIN_COIN_SPAWN_TIME = 1f;
    private static final float MAX_COIN_SPAWN_TIME = 6f;
    private static final int ICON_WIDTH = 50;
    private static final int ICON_HEIGHT = 50;
    private static final int ICON_Y = 650;
    private float deltaTime = 0;
    private Texture singleCoin;
    BitmapFont font;
    BitmapFont snakeNumber;
    BitmapFont ballNumber;
    private Texture play, pause;

    Random random;
    public int generateSquareColors = 0;
    private boolean pausedGame = false;

    public GameView(final SnakeSmash game, float speed) {
        this.game = game;
        GameController.getInstance().speed = speed;
        GameController.getInstance().saveSpeed=speed;
        loadAssets();
        font = game.getFont();
        this.snakeNumber=game.getFont();
        this.ballNumber=game.getFont();
        singleCoin = new Texture("singleCoin.png");
        play = new Texture("play.png");
        pause = new Texture("pause.png");

        random = new Random();
        positions = new ArrayList<Position>();
        squareSpawnTimer = random.nextFloat() * (MAX_SQUARE_SPAWN_TIME - MIN_SQUARE_SPAWN_TIME) + MIN_SQUARE_SPAWN_TIME;
        ballSpawnTimer = random.nextFloat() * (MAX_BALL_SPAWN_TIME - MIN_BALL_SPAWN_TIME) + MIN_BALL_SPAWN_TIME;
        coinSpawnTimer=  random.nextFloat() * (MAX_COIN_SPAWN_TIME - MIN_COIN_SPAWN_TIME) + MIN_COIN_SPAWN_TIME;
        game.scrollingBackground.setSpeedFixed(false);



        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                int x = 10;
                if (game.camera.getInputInGameWorld().x < x + ICON_WIDTH && game.camera.getInputInGameWorld().x > x && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y < ICON_Y + ICON_HEIGHT && SCREEN_HEIGHT - game.camera.getInputInGameWorld().y > ICON_Y) {

                    if (!pausedGame) {
                        GameController.getInstance().speed = 0;
                        pausedGame = true;
                    } else {
                        GameController.getInstance().speed = GameController.getInstance().saveSpeed;
                        pausedGame = false;
                    }
                }
                return super.touchUp(screenX,screenY,pointer,button);
                }



        });
    }

    private void loadAssets() {

        this.game.getAssetManager().load("background.png", Texture.class);
        this.game.getAssetManager().load("bigWall.png", Texture.class);
        this.game.getAssetManager().load("mediumWall.png", Texture.class);
        this.game.getAssetManager().load("blueSquare.png", Texture.class);
        this.game.getAssetManager().load("greenSquare.png", Texture.class);
        this.game.getAssetManager().load("pinkSquare.png", Texture.class);
        this.game.getAssetManager().load("yellowSquare.png", Texture.class);
        this.game.getAssetManager().load("redSquare.png", Texture.class);
        this.game.getAssetManager().load("lightpinkSquare.png", Texture.class);
        this.game.getAssetManager().load("mustardSquare.png", Texture.class);
        this.game.getAssetManager().load("whiteBall.png", Texture.class);
        this.game.getAssetManager().load("0.png", Texture.class);
        this.game.getAssetManager().load("1.png", Texture.class);
        this.game.getAssetManager().load("2.png", Texture.class);
        this.game.getAssetManager().load("3.png", Texture.class);
        this.game.getAssetManager().load("4.png", Texture.class);
        this.game.getAssetManager().load("5.png", Texture.class);
        this.game.getAssetManager().load("6.png", Texture.class);
        this.game.getAssetManager().load("7.png", Texture.class);
        this.game.getAssetManager().load("8.png", Texture.class);
        this.game.getAssetManager().load("9.png", Texture.class);
        this.game.getAssetManager().load("coin.png",Texture.class);



        this.game.getAssetManager().load("gameMusic.mp3", Music.class);
        this.game.getAssetManager().load("coinSound.mp3", Sound.class);
        this.game.getAssetManager().finishLoading();

    }

    @Override
    public void show() {
        music = game.getAssetManager().get("gameMusic.mp3");
        coinSound = game.getAssetManager().get("coinSound.mp3");
        music.setLooping(true);

        if(game.getMusic()){
            music.play();
        }
    }

    public void render(float delta) {

        if(GameController.getInstance().gameOver) {
            this.dispose();
            music.stop();
            game.setScreen(new GameOverMenu(game, GameController.getInstance().getScore()));
            return;
        }

        Gdx.gl.glClearColor(103 / 255f, 69 / 255f, 117 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        game.getBatch().begin();

        game.scrollingBackground.updateAndRender(delta, game.getBatch());
        drawEntities();
        drawCoin(delta);
        drawBalls();
        drawSquares();
        drawCoinNumber();
        drawScore();

        if(pausedGame)
            game.getBatch().draw(play,20,ICON_Y);
        else
            game.getBatch().draw(pause,20,ICON_Y);

        game.getBatch().end();

        if (GameController.getInstance().speed !=0) {
            squareSpawnTimer -= delta;
            ballSpawnTimer -= delta;
            coinSpawnTimer-=delta;
        }
        int showOrNot;
        showOrNot=random.nextInt(3);
        if (showOrNot ==0) {
            if (squareSpawnTimer <= 0) {
                deltaTime++;
                squareSpawnTimer = random.nextFloat() * (MAX_SQUARE_SPAWN_TIME - MIN_SQUARE_SPAWN_TIME) + MIN_SQUARE_SPAWN_TIME;
                for (int i = 0; i < 5; i++) {
                    showOrNot = random.nextInt(2);
                    if ((showOrNot == 1 || deltaTime == 6) && GameController.getInstance().speed > 0)
                        defineSquareColors(i);
                }
                if (deltaTime == 6)
                    deltaTime = 0;
            }
        }
        else if (showOrNot==1){
            if (ballSpawnTimer <= 0) {
                ballSpawnTimer = random.nextFloat() * (MAX_BALL_SPAWN_TIME - MIN_BALL_SPAWN_TIME) + MIN_BALL_SPAWN_TIME;
                for (int i = 0; i < 3; i++) {
                    showOrNot = random.nextInt(2);
                    if (showOrNot == 1 && GameController.getInstance().speed > 0) {
                        GameModel.getInstance().createBall(i * 4 + 3, 15, i * 2 + 1);

                    }
                }

            }
        }
        else{
            if (coinSpawnTimer<=0){
                coinSpawnTimer=  random.nextFloat() * (MAX_COIN_SPAWN_TIME - MIN_COIN_SPAWN_TIME) + MIN_COIN_SPAWN_TIME;
                GameModel.getInstance().createCoin(10,35);

            }
        }

        if(!GameController.getInstance().gameOver){
            handleInputs(delta);
            GameController.getInstance().updateCoin(delta);
            GameController.getInstance().updateSquares(delta);
            GameController.getInstance().updateBalls(delta);
            GameController.getInstance().detectCollisionCoins();
            GameController.getInstance().detectCollisionSquare(delta);
            GameController.getInstance().detectCollisionBalls();
            if (GameModel.getInstance().getSnake(). collideWithSquare && game.getVibration())
                Gdx.input.vibrate(200);
            if (GameController.getInstance().catchCoin){
                coinSound.play();
                GameController.getInstance().catchCoin=false;
            }

        }



    }



    @Override
    public void resize(int width, int height) {

    }


private void drawCoin(float delta){
       // System.out.println("COIN SIZE:::"+ GameModel.getInstance().getCoins().size());
           EntityView view;
   for (CoinModel coin : GameModel.getInstance().getCoins()){
        view = ViewFactory.makeView(game, coin);
        view.update(coin);
        CoinView coinView=(CoinView)view;
        coinView.updateCoin(delta);
        view.draw(game.getBatch());
    }
}
    private void drawEntities() {
        EntityView view;
        view = ViewFactory.makeView(game, GameModel.getInstance().getSnake());
        view.update(GameModel.getInstance().getSnake());
        view.draw(game.getBatch());
       snakeNumber.draw(game.getBatch(),""+GameModel.getInstance().getSnake().getSize(), (GameModel.getInstance().getSnake().getX()-1)*480/18.7f, 720*15/37f);
        Position actual = new Position(GameModel.getInstance().getSnake().getX(),GameModel.getInstance().getSnake().getY());
        positions.add(0,actual);
        for (int i =1; i < GameModel.getInstance().getSnake().getSize();i++){
            if (i*5<positions.size()) {
                BallModel ball = new BallModel(positions.get(i *5).getX(), 10-i, 0, 0);
                view.update(ball);
                view.draw(game.getBatch());
            }
        }
        //System.out.println();
     }

    private void handleInputs(float delta) {
        if (isRight()|| isJustRight())
            GameController.getInstance().shiftRight(GameController.getInstance().speed);
       else if (isLeft() || isJustLeft())
            GameController.getInstance().shiftLeft(GameController.getInstance().speed);


    }
    public boolean isRight(){
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT) || (Gdx.input.isTouched() && Gdx.input.getX()>=SnakeSmash.WIDTH/2);
    }
    public boolean isLeft(){
        return Gdx.input.isKeyPressed(Input.Keys.LEFT) || (Gdx.input.isTouched() && Gdx.input.getX()<SnakeSmash.WIDTH/2);


    }
    public boolean isJustRight(){
        return Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) || (Gdx.input.justTouched() && Gdx.input.getX()>SnakeSmash.WIDTH/2);


    }
    public boolean isJustLeft(){
        return Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || (Gdx.input.justTouched() && Gdx.input.getX()<SnakeSmash.WIDTH/2);


    }

    private void drawSquares() {
        EntityView view;
        for (SquareModel square : GameModel.getInstance().getSquares()) {
            view = ViewFactory.makeView(game, square);
            view.update(square);
            view.draw(game.getBatch());
            for (NumberModel number : square.numbers){
                view = ViewFactory.makeView(game, number);
                view.update(number);
                view.draw(game.getBatch());
            }
        }
    }
    private void drawBalls(){
        EntityView view;

        for (BallModel ball : GameModel.getInstance().getBalls()) {
            view = ViewFactory.makeView(game, ball);
            view.update(ball);
            view.draw(game.getBatch());
        }
    }
    public void defineSquareColors(int i) {
        generateSquareColors++;
        if (generateSquareColors == 7) generateSquareColors = 0;
        switch (generateSquareColors) {
            case 0:
                GameModel.getInstance().createSquare(i * 4 + 1.9f, 30, random.nextInt(3) * 2 + 1,EntityModel.ModelType.GREENSQUARE);
                break;
            case 1:
                GameModel.getInstance().createSquare(i * 4 + 1.9f, 30, random.nextInt(7) * 2 + 1,EntityModel.ModelType.PINKSQUARE);
                break;
            case 2:
                GameModel.getInstance().createSquare(i * 4 + 1.9f, 30, random.nextInt(5) * 2 + 1, EntityModel.ModelType.LIGHTPINKSQUARE);
                break;
            case 3:
                GameModel.getInstance().createSquare(i * 4 + 1.9f, 30, random.nextInt(6) * 2 + 1,EntityModel.ModelType.BLUESQUARE);
                break;
            case 4:
                GameModel.getInstance().createSquare(i * 4 + 1.9f, 30, random.nextInt(4) * 2 + 1,EntityModel.ModelType.REDSQUARE);
                break;
            case 5:
                GameModel.getInstance().createSquare(i * 4 + 1.9f, 30, random.nextInt(6) * 2 + 1,EntityModel.ModelType.YELLOWSQUARE);
                break;
            case 6:
                GameModel.getInstance().createSquare(i * 4 + 1.9f, 30, i * 2 + 1,EntityModel.ModelType.MUSTARDSQUARE);
                break;
        }
    }


    public void drawScore(){
        int score = GameController.getInstance().getScore();

        if(score >= 100)
            font.draw(game.getBatch(),""+score, 410, 710);
        else if (score >= 10)
            font.draw(game.getBatch(),""+score, 420, 710);
        else
            font.draw(game.getBatch(),""+score, 430, 710);
    }

    public void drawCoinNumber(){
        int coins = GameController.getInstance().getCoins();

        game.getBatch().draw(singleCoin,370, 643);

        if(coins >= 100)
            font.draw(game.getBatch(), ""+coins, 410, 670);
        else if(coins >= 10)
            font.draw(game.getBatch(), ""+coins, 420, 670);
        else
            font.draw(game.getBatch(), ""+coins, 430, 670);


    }

 /*  public Sound getCoinSound(){
        return coinSound;
    }*/
}