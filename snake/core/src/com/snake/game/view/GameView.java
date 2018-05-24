package com.snake.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.snake.game.Position;
import com.snake.game.SnakeSmash;
import com.snake.game.controller.GameController;
import com.snake.game.model.GameModel;
import com.snake.game.model.entities.BallModel;
import com.snake.game.model.entities.CoinModel;
import com.snake.game.model.entities.EntityModel;
import com.snake.game.model.entities.NumberModel;
import com.snake.game.model.entities.SquareModel;
import com.snake.game.model.entities.WallModel;
import com.snake.game.view.entities.CoinView;
import com.snake.game.view.entities.EntityView;
import com.snake.game.view.entities.ViewFactory;
import com.snake.game.view.menus.GameOverMenu;

import java.util.ArrayList;
import java.util.Random;


public class GameView extends ScreenAdapter {
    public final static float PIXEL_TO_SQUARE = 0.04f;
    public static final float ANIMATION_COIN_SPEED=0.1f;
    private final SnakeSmash game;
    public ArrayList<Position> positions;
    private Music music;
    private float squareSpawnTimer;
    private float ballSpawnTimer;
    private float coinSpawnTimer;
    private static final float MIN_SQUARE_SPAWN_TIME = 0.6f;
    private static final float MAX_SQUARE_SPAWN_TIME = 4f;
    private static final float MIN_BALL_SPAWN_TIME = 1f;
    private static final float MAX_BALL_SPAWN_TIME = 3f;
    private static final float MIN_COIN_SPAWN_TIME = 1f;
    private static final float MAX_COIN_SPAWN_TIME = 3f;
    int coinNumber=0;
    private float deltaTime = 0;
    BitmapFont scoreFont;

    Random random;
    public int generateSquareColors = 0;

    public GameView(SnakeSmash game, int speed) {
        this.game = game;
        GameController.getInstance().speed = speed;
        loadAssets();
        scoreFont = game.getFont();

        random = new Random();
        positions = new ArrayList<Position>();
        squareSpawnTimer = random.nextFloat() * (MAX_SQUARE_SPAWN_TIME - MIN_SQUARE_SPAWN_TIME) + MIN_SQUARE_SPAWN_TIME;
        ballSpawnTimer = random.nextFloat() * (MAX_BALL_SPAWN_TIME - MIN_BALL_SPAWN_TIME) + MIN_BALL_SPAWN_TIME;
        coinSpawnTimer=  random.nextFloat() * (MAX_COIN_SPAWN_TIME - MIN_COIN_SPAWN_TIME) + MIN_COIN_SPAWN_TIME;
        game.scrollingBackground.setSpeedFixed(false);

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
        this.game.getAssetManager().finishLoading();

    }

    @Override
    public void show() {
        music = game.getAssetManager().get("gameMusic.mp3");
        music.setLooping(true);
        if(game.getMusic()){
            music.play();
        }
    }

    public void render(float delta) {
        GameController.getInstance().update(delta);

        if(GameController.getInstance().gameOver) {
            this.dispose();
            game.setScreen(new GameOverMenu(game, GameController.getInstance().getScore()));
            return;
        }

        Gdx.gl.glClearColor(103 / 255f, 69 / 255f, 117 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        game.getBatch().begin();
        game.scrollingBackground.updateAndRender(delta, game.getBatch());
        drawEntities();
        drawScore();
        drawCoin(delta);
        //drawWalls();

        drawSquares();
        drawBalls();
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
                        GameModel.getInstance().createBall(i * 4 + 3, 35, i * 2 + 1);
                        GameModel.getInstance().createWall(i * 4 + 3, 40);

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
            GameController.getInstance().updateWalls(delta);
            GameController.getInstance().updateSquares(delta);
            GameController.getInstance().updateBalls(delta);
            GameController.getInstance().detectCollisionSquare(delta);
            GameController.getInstance().detectCollisionBalls(delta);
            GameController.getInstance().detectCollisionWalls(delta);
        }



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
        view = ViewFactory.makeView(game, GameModel.getInstance().snakeBalls.get(0));
        view.update(GameModel.getInstance().snakeBalls.get(0));
        view.draw(game.getBatch());
        Position actual = new Position(GameModel.getInstance().snakeBalls.get(0).getX(),GameModel.getInstance().snakeBalls.get(0).getY());
        positions.add(0,actual);
        //System.out.print("Add: " + actual.getX());

        for (int i =1; i < GameModel.getInstance().snakeBalls.size();i++){
            if (i*5<positions.size()) {
                BallModel ball = new BallModel(positions.get(i *5).getX(), 10-i, 0, 0);
                view.update(ball);
                view.draw(game.getBatch());
            }
        }
        System.out.println();
     }
    private void drawWalls(){
        EntityView view;
        for (WallModel wall : GameModel.getInstance().getWalls()){
            view = ViewFactory.makeView(game, wall);
            view.update(wall);
            view.draw(game.getBatch());
        }
    }

    private void handleInputs(float delta) {
        if (isRight()|| isJustRight())
            GameController.getInstance().shiftRight(delta,GameController.getInstance().speed);
       else if (isLeft() || isJustLeft())
            GameController.getInstance().shiftLeft(delta,GameController.getInstance().speed);
        else
            GameController.getInstance().noInput(delta);

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
                GameModel.getInstance().createSquare(i * 4 + 1.9f, 35, i * 2 + 1,EntityModel.ModelType.GREENSQUARE);
                break;
            case 1:
                GameModel.getInstance().createSquare(i * 4 + 1.9f, 35, i * 2 + 1,EntityModel.ModelType.PINKSQUARE);
                break;
            case 2:
                GameModel.getInstance().createSquare(i * 4 + 1.9f, 35, i * 2 + 1, EntityModel.ModelType.LIGHTPINKSQUARE);
                break;
            case 3:
                GameModel.getInstance().createSquare(i * 4 + 1.9f, 35, i * 2 + 1,EntityModel.ModelType.BLUESQUARE);
                break;
            case 4:
                GameModel.getInstance().createSquare(i * 4 + 1.9f, 35, i * 2 + 1,EntityModel.ModelType.REDSQUARE);
                break;
            case 5:
                GameModel.getInstance().createSquare(i * 4 + 1.9f, 35, i * 2 + 1,EntityModel.ModelType.YELLOWSQUARE);
                break;
            case 6:
                GameModel.getInstance().createSquare(i * 4 + 1.9f, 35, i * 2 + 1,EntityModel.ModelType.MUSTARDSQUARE);
                break;
        }
    }


    public void drawScore(){
        int score = GameController.getInstance().getScore();
        System.out.println(score);

        //scoreFont.draw(game.getBatch(),"Score\n"+score, 385, 700);
    }
}