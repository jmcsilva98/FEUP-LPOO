package com.snake.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static com.snake.game.view.GameView.PIXEL_TO_SQUARE;

/**
 * Game camera class
 */
public class GameCamera {
    private static final boolean DEBUG_PHYSICS = false;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    private StretchViewport viewport;
    private Matrix4 debugCamera;

    /**
     * Game Camera constructor
     * @param width camera width
     * @param height camer height
     */
    public GameCamera (int width, int height) {
        camera = new OrthographicCamera();
        debugRenderer= new Box2DDebugRenderer();
        viewport = new StretchViewport(width, height, camera);
        viewport.apply();
        camera.position.set(width / 2, height / 2, 0);
        camera.update();
        if (DEBUG_PHYSICS) {
            debugRenderer = new Box2DDebugRenderer();
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_SQUARE);
        }
    }

    /**
     * Combined camera
     * @return combined camera
     */

    public Matrix4 combined() {
        return camera.combined;
    }

    /**
     * Function to update viewport
     * @param width width
     * @param height height
     */

    public void update (int width, int height) {
        viewport.update(width, height);
    }

    /**
     * Function to get input in  game world
     * @return vector2
     */
    public Vector2 getInputInGameWorld () {
        Vector3 inputScreen = new Vector3(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);
        Vector3 unprojected = camera.unproject(inputScreen);
        return new Vector2(unprojected.x, unprojected.y);
    }

}
