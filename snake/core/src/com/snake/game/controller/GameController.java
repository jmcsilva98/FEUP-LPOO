package com.snake.game.controller;
import com.badlogic.gdx.Gdx;
import com.snake.game.model.GameModel;
import com.snake.game.model.entities.BallModel;
import com.snake.game.model.entities.CoinModel;
import com.snake.game.model.entities.NumberModel;
import com.snake.game.model.entities.SquareModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Game controller class
 */

public class GameController {

    private static GameController instance;

    private int score;
    private final SnakeBody snakeBody;
    private List<BallModel> ballsToRemove;
    private ArrayList<CoinModel> coinsToRemove;
    public boolean catchCoin=false;
    private ArrayList<SquareModel> squaresToRemove;
    public float speed;
    public float saveSpeed;
    public static final int SCREEN_WIDTH = 480;
    public static final int SCREEN_HEIGHT = 720;
    public static final int SNAKE_WIDTH_PIXEL = 17;
    public static final int SNAKE_HEIGHT_PIXEL = 32;
    public static final int SNAKE_WIDTH = SNAKE_WIDTH_PIXEL * 3;
    public static final int SNAKE_HEIGHT = SNAKE_HEIGHT_PIXEL * 3;
    private int coins;
    public boolean gameOver = false;

    /**
     * Game controller constructor
     */

    private GameController() {
        score = 0;
        coins = 0;
        saveSpeed = speed;

        ballsToRemove = new ArrayList<BallModel>();
        coinsToRemove = new ArrayList<CoinModel>();
        squaresToRemove = new ArrayList<SquareModel>();
    }

    /**
     * Function to get game instance
     * @return instance
     */

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }


    /**
     * Function to shift snake to the right called by handle input.
     * @param speed speed to calculate new snake position
     */

    public void shiftRight(float speed) {
        float x = GameModel.getInstance().getSnake().getX() + (speed *1.5f)* Gdx.graphics.getDeltaTime();

        if (x > 18.7)
            x = (float) 18.7;
        for (SquareModel square :GameModel.getInstance().getSquares()){
            if ( x > square.getX()-2.8f && x<=square.getX()-2.4f && x<square.getX() && square.getY() - 2.8 <= GameModel.getInstance().getSnake().getY() && GameModel.getInstance().getSnake().getY() <= square.getY()+2.8) {
                x = square.getX() - 2.6f;
                if (x<0.48){
                    x =0.48f;
                }

            }
        }
        GameModel.getInstance().getSnake().setX(x);


    }

    /**
     * Function to shift snake to the left called by handle input.
     * @param speed speed to calculate new snake position
     */

    public void shiftLeft(float speed) {
        float x = GameModel.getInstance().getSnake().getX() - (speed *1.5f)* Gdx.graphics.getDeltaTime();
        if (x < 0.48)
            x = (float) 0.48;

        for (SquareModel square :GameModel.getInstance().getSquares()){
            if ( x > square.getX()+2f&& x<square.getX()+2.4f && x>square.getX() && square.getY() - 2.4 <= GameModel.getInstance().getSnake().getY() && GameModel.getInstance().getSnake().getY() <= square.getY()+2.8) {
                x = square.getX() + 2.6f;
                if (x>18.7){
                    x =18.7f;
                }

            }
        }
        GameModel.getInstance().getSnake().setX(x);
}

    /**
     * Function to update squares positions.
     * @param delta time since last update(ms)
     */

    public void updateSquares(float delta) {

        for (SquareModel square : GameModel.getInstance().getSquares()) {
            square.update(delta, speed);
            for (NumberModel number : square.numbers){
                number.update(delta,speed);
            }
            if (square.toRemove)
                squaresToRemove.add(square);
        }

    }

    /**
     * Function to update balls positions
     * @param delta time since last update(ms)
     */

    public void updateBalls(float delta){
        for (BallModel ball : GameModel.getInstance().getBalls()){
            ball.update(delta,speed);
            if (ball.toRemove)
                ballsToRemove.add(ball);
        }
    }

    /**
     * Function to update coins positions
     * @param delta time since last update (ms)
     */
    public void updateCoin(float delta){
        for (CoinModel coin : GameModel.getInstance().getCoins()){
            coin.update(delta,speed);
            if (coin.toRemove)
                coinsToRemove.add(coin);
        }
    }

    /**
     * Function to detect collision between squares and snake
     * @param delta delta time
     */
    public void detectCollisionSquare(float delta) {

        for (SquareModel square : GameModel.getInstance().getSquares()) {

            if (square.getY() - 2.3 < GameModel.getInstance().getSnake().getY() && GameModel.getInstance().getSnake().getX() > square.getX()-2.3f && GameModel.getInstance().getSnake().getX() < square.getX() + 1.9f && GameModel.getInstance().getSnake().getY() < square.getY()) {
                speed = 0;
                GameModel.getInstance().getSnake().collideWithSquare=true;
                    if (square.getValue() == 0) {
                        squaresToRemove.add(square);
                        speed = saveSpeed;
                        GameModel.getInstance().getSnake().collideWithSquare=false;
                    } else
                        decrementSquare(delta, square);
            }
        }
        GameModel.getInstance().getSquares().removeAll(squaresToRemove);
    }

    /**
     * Function to detect collision between snake and balls
     */
    public void detectCollisionBalls(){
        for (BallModel ball : GameModel.getInstance().getBalls()) {
            if (ball.getY() -0.2 < GameModel.getInstance().getSnake().getY() && ball.getX() < GameModel.getInstance().getSnake().getX() + 0.8 && GameModel.getInstance().getSnake().getX() < ball.getX() + 0.8 && GameModel.getInstance().getSnake().getY() < ball.getY()) {
                GameModel.getInstance().getSnake().updateSize(1);
                ballsToRemove.add(ball);
            }
        }
        GameModel.getInstance().getBalls().removeAll(ballsToRemove);

    }

    /**
     * Function to detect collision between snake and coins
     */
    public void detectCollisionCoins(){
        for (CoinModel coin : GameModel.getInstance().getCoins()){

            if (coin.getY()-0.2 < GameModel.getInstance().getSnake().getY() && coin.getX() < GameModel.getInstance().getSnake().getX() + 0.8 && GameModel.getInstance().getSnake().getX() < coin.getX() + 0.8 && GameModel.getInstance().getSnake().getY() < coin.getY()){
                coins++;
                score+=5;
                catchCoin=true;
                coinsToRemove.add(coin);
            }
        }
        GameModel.getInstance().getCoins().removeAll(coinsToRemove);
    }

    /**
     * Function to decrement square when snake collides with 
     * @param delta
     * @param square
     */

    public void decrementSquare(float delta, SquareModel square) {
        square.time_to_decrement -= delta * 10;
        if (square.time_to_decrement <= 0 && GameModel.getInstance().getSnake().getSize()>0) {
            square.setValue(square.getValue() - 1);
            score += 2;
            GameModel.getInstance().calculateNumbers(square);
            square.time_to_decrement = 2;
            GameModel.getInstance().getSnake().updateSize(-1);
            GameModel.getInstance().deleteBallToSnake();
        }
        if (GameModel.getInstance().getSnake().getSize()<=0){
            gameOver = true;
        }
    }

    public static void restart(){
        instance = null;
    }
    public int getScore(){
        return score;
    }
    public int getCoins() {return coins;}
    public ArrayList<CoinModel> getCoinsToRemove(){return coinsToRemove;}
    public List <BallModel> getBallsToRemove() {return ballsToRemove;}
    public ArrayList <SquareModel> getSquaresToRemove() {return squaresToRemove;}
    public void setScore(int score){this.score = score;}
    public void setCoins(int coins) {this.coins = coins;}
}
