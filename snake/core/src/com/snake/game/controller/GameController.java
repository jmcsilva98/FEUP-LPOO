package com.snake.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.snake.game.controller.entities.SnakeBody;
import com.snake.game.controller.entities.SquareBody;
import com.snake.game.model.GameModel;
import com.snake.game.model.entities.BallModel;
import com.snake.game.model.entities.EntityModel;

import java.util.ArrayList;
import java.util.List;

public class GameController implements ContactListener {

    private static GameController instance;

    private final World world;
    private final SnakeBody snakeBody;
    private List<BallModel> ballToAdd = new ArrayList<BallModel>();
    private List<SquareBody> squaresToAdd = new ArrayList<SquareBody>();
    private float accumulator;
    public static final int SCREEN_WIDTH = 50;
    public static final int SCREEN_HEIGHT = 100;

    private GameController() {
        world = new World(new Vector2(0, 0), true);
        snakeBody = new SnakeBody(world, GameModel.getInstance().getSnake());
        List<BallModel> balls = GameModel.getInstance().getBalls();
        world.setContactListener(this);
    }

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public void removeFlagged() {
    }

    public World getWorld() {
        return world;
    }

    public void update(float delta) {
        GameModel.getInstance().update(delta);
        /*world.step(delta, 6, 2);
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for(Body  body:bodies) {

            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);

        }*/
    }
    public void shiftRight(float delta){
        GameModel.getInstance().getSnake().setX( GameModel.getInstance().getSnake().getX()+4 * Gdx.graphics.getDeltaTime());
    
    }


    public void shiftLeft(float delta) {
        GameModel.getInstance().getSnake().setX( GameModel.getInstance().getSnake().getX()-4 * Gdx.graphics.getDeltaTime());
    }

    public void shiftUp(float delta) {
        GameModel.getInstance().getSnake().setY( GameModel.getInstance().getSnake().getY()+4 * Gdx.graphics.getDeltaTime());
    }

    public void shiftDown(float delta) {
    }
}
