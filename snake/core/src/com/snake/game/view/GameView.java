package com.snake.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.snake.game.SnakeSmash;
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


    public GameView(SnakeSmash game){
    this.game=game;
    loadAssets();
    this.camera= createCamera();

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

    }
  /*  private void render(float delta){
        //GameController.getInstance()

    }*/

    private void drawBackground() {
        Texture background = game.getAssetManager().get("background.png", Texture.class);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        game.getBatch().draw(background, 0, 0, 0, 0, (int)(SCREEN_WIDTH / PIXEL_TO_METER), (int) (SCREEN_HEIGHT / PIXEL_TO_METER));
    }
}
