package com.snake.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.snake.game.SnakeSmash;
import com.snake.game.controller.GameController;
import com.snake.game.model.GameModel;
import com.snake.game.model.entities.SnakeModel;
import com.snake.game.view.entities.EntityView;
import com.snake.game.view.entities.ViewFactory;


import static com.snake.game.controller.GameController.SCREEN_HEIGHT;
import static com.snake.game.controller.GameController.SCREEN_WIDTH;


public class GameView extends ScreenAdapter {
    private static final boolean DEBUG_PHYSICS = false;

    public final static float PIXEL_TO_METER=0.04f;

    private static final float VIEWPORT_WIDTH = 30;

    private final SnakeSmash game;

    private final OrthographicCamera camera;

    private Box2DDebugRenderer debugRenderer;

    private Matrix4 debugCamera;
    private SnakeModel snake;


    public GameView(SnakeSmash game){
    this.game=game;
    loadAssets();
    this.camera= createCamera();
    this.snake= new SnakeModel(1,10,10,0);

}
    private OrthographicCamera createCamera(){
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));
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
    public void render(float delta){
        GameController.getInstance().removeFlagged();
      //  GameController.getInstance().update(delta);

        camera.position.set(GameModel.getInstance().getSnake().getX() / PIXEL_TO_METER, GameModel.getInstance().getSnake().getY() / PIXEL_TO_METER, 0);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor( 103/255f, 69/255f, 117/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        game.getBatch().begin();
        drawBackground();
        drawEntities();
        game.getBatch().end();

        if (DEBUG_PHYSICS) {
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
            debugRenderer.render(GameController.getInstance().getWorld(), debugCamera);
        }

    }

    private void drawEntities() {
        int size =GameModel.getInstance().getSnake().getSize();
        EntityView view;
        view = ViewFactory.makeView(game,snake);
        view.update(snake);
        view.draw(game.getBatch());

    }

    private void drawBackground() {
        Texture background = game.getAssetManager().get("background.png", Texture.class);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        game.getBatch().draw(background, 0, 0,camera.viewportWidth,camera.viewportHeight);
    }
}
