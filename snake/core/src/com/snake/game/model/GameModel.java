package com.snake.game.model;

import com.snake.game.model.entities.BallModel;
import com.snake.game.model.entities.CoinModel;
import com.snake.game.model.entities.EntityModel;
import com.snake.game.model.entities.NumberModel;
import com.snake.game.model.entities.SnakeModel;
import com.snake.game.model.entities.SquareModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Game model class
 */
public class GameModel {

    private static GameModel instance;
    private SnakeModel snake;
    private List<BallModel> balls;
    public List<BallModel> snakeBalls;
    private List<SquareModel> squares;
    private List<CoinModel> coins;

    /**
     * Function to get game model instance
     * @return instance
     */

    public static GameModel getInstance(){
        if (instance==null)
            instance = new GameModel();
        return instance;
    }

    /**
     * Game model constructor
     */
    public GameModel(){
        snake = new SnakeModel(20,10,10);
        balls= new ArrayList<BallModel>();
        squares = new ArrayList<SquareModel>();
        coins = new ArrayList<CoinModel>();
        snakeBalls=new ArrayList<BallModel>();

    }

    /**
     * Function to get snake
     * @return Snake model
     */
    public SnakeModel getSnake(){
        return snake;
    }

    /**
     * Function to get balls
     * @return list of current balls
     */
    public List<BallModel> getBalls(){
        return balls;
    }

    /**
     * Function to get current squares
     * @return list of current squares
     */
    public List<SquareModel> getSquares() {return squares;}

    /**
     * Function to get current coins
     * @return current coins
     */
    public List<CoinModel> getCoins(){return coins;}

    /**
     * Function to set snake
     * @param snake snake to be setted
     */
    public void setSnake(SnakeModel snake) {
        this.snake = snake;
    }

    /**
     * Function to set balls
     * @param balls to be setted
     */
    public void setBalls(List<BallModel> balls) {
        this.balls = balls;
    }

    /**
     * Function to set coins
     * @param coins coins to be setted
     */
    public void setCoins(List<CoinModel> coins) {
        this.coins = coins;
    }

    /**
     * Function to set squares
     * @param squares squares to be setted
     */
    public void setSquares(List<SquareModel> squares) {
        this.squares = squares;
    }

    /**
     * Function to create new square
     * @param x horizontal position
     * @param y vertical position
     * @param value square value
     * @param model model of square (different colors)
     */

    public void createSquare (float x, float y, int value,EntityModel.ModelType model) {
        SquareModel square = new SquareModel(x, y, value, model);
        calculateNumbers(square);
        squares.add(square);


    }

    /**
     * Function to calculate number to draw on square
     * @param square square where numbers will be drawn up
     */

    public void calculateNumbers(SquareModel square) {
        square.numbers.clear();
        int i = square.getValue()/10;


        int j = square.getValue () %10;
        if (i>0) {
            whichNumber(square, i, square.getX()-0.5f);
            whichNumber(square, j, square.getX()+0.5f);

        }
        else
        {
            whichNumber(square, j, square.getX());
        }
    }

    /**
     * Function to choose the corresponding model type number
     * @param square square where number will be drawn up
     * @param i
     * @param x
     */
    public void whichNumber(SquareModel square, int i, float x) {
        switch (i) {
            case 0:
                square.numbers.add(new NumberModel(x, square.getY(), 0, EntityModel.ModelType.ZERO));
                break;
            case 1:
                square.numbers.add(new NumberModel(x, square.getY(), 0, EntityModel.ModelType.ONE));
                break;
            case 2:
                square.numbers.add(new NumberModel(x, square.getY(), 0, EntityModel.ModelType.TWO));
                break;
            case 3:
                square.numbers.add(new NumberModel(x, square.getY(), 0, EntityModel.ModelType.THREE));
                break;
            case 4:
                square.numbers.add(new NumberModel(x, square.getY(), 0, EntityModel.ModelType.FOUR));
                break;
            case 5:
                square.numbers.add(new NumberModel(x, square.getY(), 0, EntityModel.ModelType.FIVE));
                break;
            case 6:
                square.numbers.add(new NumberModel(x, square.getY(), 0, EntityModel.ModelType.SIX));
                break;

            case 7:
                square.numbers.add(new NumberModel(x, square.getY(), 0, EntityModel.ModelType.SEVEN));
                break;
            case 8:
                square.numbers.add(new NumberModel(x, square.getY(), 0, EntityModel.ModelType.EIGHT));
                break;
            case 9:
                square.numbers.add(new NumberModel(x, square.getY(), 0, EntityModel.ModelType.NINE));
                break;


        }

    }

    /**
     * Function to create ball
     * @param x horizontal position
     * @param y vertical position
     * @param value value of ball
     */
    public void createBall(float x, float y, int value){
        BallModel ball;
        ball= new BallModel(x,y, value);
        balls.add(ball);
    }

    /**
     * Function to create coin
     * @param x horizontal position
     * @param y vertical position
     */

    public void createCoin(float x, float y) {
        CoinModel coin;
        coin =new CoinModel(x,y,0,0);
        coins.add(coin);
    }

    /**
     * Function to restart game model
     */

    public static void restart(){
        instance = null;

    }
}
