package com.snake.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.snake.game.SnakeSmash;
import com.snake.game.controller.GameController;
import com.snake.game.model.GameModel;
import com.snake.game.model.entities.SnakeModel;
import com.snake.game.model.entities.SquareModel;
import com.snake.game.view.entities.EntityView;
import com.snake.game.view.entities.ViewFactory;

import java.util.ArrayList;
import java.util.Random;


public class GameView implements Screen {
    public final static float PIXEL_TO_METER=0.04f;

    private final SnakeSmash game;

    private float squareSpawnTimer;
    private static final float MIN_SQUARE_SPAWN_TIME=2f;
    private static final float MAX_SQUARE_SPAWN_TIME=4f;

    Random random;

    public GameView(SnakeSmash game){
        this.game=game;
        loadAssets();
        random= new Random();
        squareSpawnTimer=random.nextFloat()*(MAX_SQUARE_SPAWN_TIME-MIN_SQUARE_SPAWN_TIME)+MIN_SQUARE_SPAWN_TIME;
        game.scrollingBackground.setSpeedFixed(false);

    }

    private void loadAssets(){

        this.game.getAssetManager().load( "background.png" , Texture.class);
        this.game.getAssetManager().load( "bigWall.png" , Texture.class);
        this.game.getAssetManager().load( "mediumWall.png" , Texture.class);
        this.game.getAssetManager().load( "blueSquare.png" , Texture.class);
        this.game.getAssetManager().load( "greenSquare.png" , Texture.class);
        this.game.getAssetManager().load( "pinkSquare.png" , Texture.class);
        this.game.getAssetManager().load( "yellowSquare.png" , Texture.class);
        this.game.getAssetManager().load( "pinkSquare.png" , Texture.class);
        this.game.getAssetManager().load( "lightpinkSquare.png" , Texture.class);
        this.game.getAssetManager().load( "mustardSquare.png" , Texture.class);
        this.game.getAssetManager().load( "whiteBall.png" , Texture.class);
        this.game.getAssetManager().finishLoading();

    }

    @Override
    public void show() {

    }

    public void render(float delta){
        //GameController.getInstance().removeFlagged();
         // GameController.getInstance().update(delta);
        handleInputs(delta);
        Gdx.gl.glClearColor( 103/255f, 69/255f, 117/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        game.getBatch().begin();
        game.scrollingBackground.updateAndRender(delta, game.getBatch());
        drawEntities();
        game.getBatch().end();
        squareSpawnTimer-=delta;
        int showOrNot;
        if (squareSpawnTimer<=0){
            squareSpawnTimer=random.nextFloat()*(MAX_SQUARE_SPAWN_TIME-MIN_SQUARE_SPAWN_TIME)+MIN_SQUARE_SPAWN_TIME;
            for (int i = 0; i<4;i++){
                showOrNot=random.nextInt(2);
                if (showOrNot==1)
                GameModel.getInstance().createSquare(i*5+2,16,i*2+1);


            }

        }
        ArrayList<SquareModel> squaresToRemove= new ArrayList<SquareModel>();
        for (SquareModel square : GameModel.getInstance().getSquares()){
            square.update(delta);
            if (square.toRemove)
                squaresToRemove.add(square);
        }

    for (SquareModel square : GameModel.getInstance().getSquares()){
        if (square.getCollisionDetect().collidesWith(GameModel.getInstance().getSnake().getCollisionDetect())) {

            if (square.getValue() < GameModel.getInstance().getSnake().getSize()) {
                squaresToRemove.add(square);
                GameModel.getInstance().getSnake().setSize(GameModel.getInstance().getSnake().getSize() - square.getValue());
            }
            System.out.println("Size:::" + GameModel.getInstance().getSnake().getSize() + ":::Square value::" + square.getValue());
        }
        }
        GameModel.getInstance().getSquares().removeAll(squaresToRemove);
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


    private void drawEntities() {
        //int size =GameModel.getInstance().getSnake().getSize();
        EntityView view;
        SnakeModel snake = GameModel.getInstance().getSnake();
        view = ViewFactory.makeView(game, snake);
        view.update(snake);
        view.draw(game.getBatch());
        for (SquareModel square :GameModel.getInstance().getSquares()) {
            view = ViewFactory.makeView(game, square);
            view.update(square);
            view.draw(game.getBatch());
        }
       view.draw(game.getBatch());
    }
    private void handleInputs(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            GameController.getInstance().shiftRight(delta);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            GameController.getInstance().shiftLeft(delta);

    }

}
