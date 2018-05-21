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
import com.snake.game.SnakeSmash;
import com.snake.game.controller.entities.SnakeBody;
import com.snake.game.controller.entities.SquareBody;
import com.snake.game.model.GameModel;
import com.snake.game.model.entities.BallModel;
import com.snake.game.model.entities.CoinModel;
import com.snake.game.model.entities.EntityModel;
import com.snake.game.model.entities.SnakeModel;
import com.snake.game.model.entities.SquareModel;
import com.snake.game.view.menus.GameOverMenu;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private static GameController instance;

    private final World world;
    private final SnakeBody snakeBody;
    private List<BallModel> ballsToRemove = new ArrayList<BallModel>();
    private ArrayList<CoinModel> coinArray = new ArrayList<CoinModel>();
    private ArrayList<SquareModel> squaresToRemove = new ArrayList<SquareModel>();
    public float speed;
    public static final int SCREEN_WIDTH = 480;
    public static final int SCREEN_HEIGHT = 720;
    public static final int SNAKE_WIDTH_PIXEL = 17;
    public static final int SNAKE_HEIGHT_PIXEL = 32;
    public static final int SNAKE_WIDTH = SNAKE_WIDTH_PIXEL * 3;
    public static final int SNAKE_HEIGHT = SNAKE_HEIGHT_PIXEL * 3;
    public int coins;

    private GameController() {
        world = new World(new Vector2(0, 0), true);
        snakeBody = new SnakeBody(world, GameModel.getInstance().getSnake());

        List<BallModel> balls = GameModel.getInstance().getBalls();
        coins = 0;
    }

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }


    public World getWorld() {
        return world;
    }

    public void update(float delta) {
        world.step(delta, 6, 2);

    }

    public void shiftRight(float delta, float speed) {
        float x = GameModel.getInstance().snakeBalls.get(0).getX() + speed * Gdx.graphics.getDeltaTime();

        if (x > 18.7)
            x = (float) 18.7;
        GameModel.getInstance().updateSnake(x);


    }


    public void shiftLeft(float delta, float speed) {
        float x = GameModel.getInstance().snakeBalls.get(0).getX() - speed * Gdx.graphics.getDeltaTime();
        if (x < 0.48)
            x = (float) 0.48;

        GameModel.getInstance().updateSnake(x);
}

    public void updateSquares(float delta) {

        for (SquareModel square : GameModel.getInstance().getSquares()) {
            square.update(delta, speed);
            if (square.toRemove)
                squaresToRemove.add(square);
        }

    }

    public void updateBalls(float delta){
        for (BallModel ball : GameModel.getInstance().getBalls()){
            ball.update(delta,speed);
            if (ball.toRemove)
                ballsToRemove.add(ball);
        }
    }

    public void detectCollisionSquare(float delta) {
        for (SquareModel square : GameModel.getInstance().getSquares()) {
            if (square.getY() - 2.3 < GameModel.getInstance().snakeBalls.get(0).getY() && GameModel.getInstance().snakeBalls.get(0).getX() + 1.9 > square.getX() && GameModel.getInstance().snakeBalls.get(0).getX() < square.getX() + 1.9 && GameModel.getInstance().snakeBalls.get(0).getY() < square.getY()) {
                speed = 0;
                if (square.getValue() == 0) {
                    squaresToRemove.add(square);
                    speed = 9;
                } else
                    decrementSquare(delta, square);
            }
        }
        GameModel.getInstance().getSquares().removeAll(squaresToRemove);
    }
    public void detectCollisionBalls(float delta){
        for (BallModel ball : GameModel.getInstance().getBalls()) {
            if (ball.getY() -0.2 < GameModel.getInstance().snakeBalls.get(0).getY() && ball.getX() < GameModel.getInstance().snakeBalls.get(0).getX() + 0.8 && GameModel.getInstance().snakeBalls.get(0).getX() < ball.getX() + 0.8 && GameModel.getInstance().snakeBalls.get(0).getY() < ball.getY()) {
                GameModel.getInstance().addBallToSnake(1);
                ballsToRemove.add(ball);
            }
        }
        GameModel.getInstance().getBalls().removeAll(ballsToRemove);

    }
    private void decrementSquare(float delta, SquareModel square) {
        square.time_to_decrement -= delta * 10;
        if (square.time_to_decrement <= 0 && GameModel.getInstance().snakeBalls.size()>0) {
            square.setValue(square.getValue() - 1);
            square.time_to_decrement = 2;
            GameModel.getInstance().deleteBallToSnake();
        }
        if (GameModel.getInstance().snakeBalls.size()<=0){
            System.out.println("GAME OVER   ");

            System.exit(0);
            //chamar game over menu
        }
    }
}
