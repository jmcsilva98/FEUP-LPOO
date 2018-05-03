package com.snake.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.snake.game.SnakeSmash;
import com.snake.game.controller.GameController;

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

    }
  /*  private void render(float delta){
        //GameController.getInstance()

    }*/

}
