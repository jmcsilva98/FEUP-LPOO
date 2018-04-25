package com.snake.game.model;

import com.snake.game.model.entities.BallModel;
import com.snake.game.model.entities.CoinModel;
import com.snake.game.model.entities.EntityModel;
import com.snake.game.model.entities.SnakeModel;
import com.snake.game.model.entities.SquareModel;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private static GameModel instance;
    private SnakeModel snake;
    private List<BallModel> balls;
    private List<SquareModel> squares;
    private List<CoinModel> coins;

    public static GameModel getInstance(){
        if (instance==null)
            instance = new GameModel();
        return instance;
    }
    public GameModel(){
        snake = new SnakeModel(6,10,10,0);
        balls= new ArrayList<BallModel>();
        squares = new ArrayList<SquareModel>();
        coins = new ArrayList<CoinModel>();
    }

    public SnakeModel getSnake(){
        return snake;
    }
    public List<BallModel> getBalls(){
        return balls;
    }
    public List<SquareModel> getSquares() {
        return squares;
    }
    public List<CoinModel> getCoins(){
        return coins;
    }
    public void createSquare (int value) {
        SquareModel square = new SquareModel(10, 10, 0, value);
        squares.add(square);

    }
    public void remove (EntityModel m1){
        if (m1 instanceof SquareModel)
            squares.remove(m1);
        else if (m1 instanceof BallModel)
            balls.remove(m1);
        else if (m1 instanceof CoinModel)
            coins.remove(m1);
    }



}
