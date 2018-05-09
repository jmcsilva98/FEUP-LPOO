package com.snake.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.snake.game.SnakeSmash;
import com.snake.game.controller.GameController;
import com.snake.game.model.GameModel;
import com.snake.game.model.entities.SnakeModel;
import com.snake.game.model.entities.SquareModel;
import com.snake.game.view.entities.EntityView;
import com.snake.game.view.entities.ViewFactory;


import static com.snake.game.controller.GameController.SCREEN_HEIGHT;
import static com.snake.game.controller.GameController.SCREEN_WIDTH;


public class GameView implements Screen {
    private static final boolean DEBUG_PHYSICS = false;

    public final static float PIXEL_TO_METER=0.04f;

    private static final float VIEWPORT_WIDTH = 30;

    private final SnakeSmash game;

  //  private final OrthographicCamera camera;

    private Box2DDebugRenderer debugRenderer;

    private Matrix4 debugCamera;
    private SnakeModel snake;
    private ShapeRenderer shape;


    public GameView(SnakeSmash game){
        this.game=game;
        loadAssets();
        //this.camera= createCamera();
       // this.snake= new SnakeModel(1,10,10,0);
        game.scrollingBackground.setSpeedFixed(false);

    }
    private OrthographicCamera createCamera(){
       OrthographicCamera camera = new OrthographicCamera(480 ,720);
        camera.position.set(camera.viewportWidth /2f, camera.viewportHeight/2f,0);
        camera.update();
        if (DEBUG_PHYSICS){
            debugRenderer=new Box2DDebugRenderer();
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1/PIXEL_TO_METER);

        }
        return camera;
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
        SquareModel square = GameModel.getInstance().getSquares().get(0);
       view.draw(game.getBatch());
    }
    private void handleInputs(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            GameController.getInstance().shiftRight(delta);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            GameController.getInstance().shiftLeft(delta);
    }
}
