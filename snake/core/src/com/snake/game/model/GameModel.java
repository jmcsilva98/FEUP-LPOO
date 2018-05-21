package com.snake.game.model;

import com.badlogic.gdx.Gdx;
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
    public List<BallModel> snakeBalls;
    private List<SquareModel> squares;
    private List<CoinModel> coins;

    public static GameModel getInstance(){
        if (instance==null)
            instance = new GameModel();
        return instance;
    }
    public GameModel(){
        snake = new SnakeModel(20,10,10,0);
        balls= new ArrayList<BallModel>();
        squares = new ArrayList<SquareModel>();
        coins = new ArrayList<CoinModel>();
        snakeBalls=new ArrayList<BallModel>();
        startSnake();
    }

    private void startSnake() {
        snakeBalls.add(new BallModel(10,10,0,0));
        for (int i =1;i<10;i++){
            addBallToSnake(i);
        }
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
    public void createSquare (float x, float y, int value,EntityModel.ModelType model) {
        SquareModel square;
        square = new SquareModel(x, y, 0,value, model);
        squares.add(square);

    }
    public void addBallToSnake(int i){
        BallModel lastBall= snakeBalls.get(snakeBalls.size()-1);
        BallModel ball = new BallModel(lastBall.getX()+0.1f*i,lastBall.getY()-1,0,0);
        snakeBalls.add(ball);
    }
    public void updateSnake(float x){
        BallModel lastBall=snakeBalls.get(0);
        BallModel thatBall;
        for (BallModel ball:snakeBalls){
            thatBall=ball;
            ball.setX(lastBall.getX());
            lastBall=thatBall;
        }

        snakeBalls.get(0).setX(x);
    }
    public void deleteBallToSnake(){
        if (snakeBalls.size()>0)
            snakeBalls.remove(snakeBalls.size()-1);



    }
    public void createBall(float x, float y, int value){
        BallModel ball;
        ball= new BallModel(x,y,0,value);
        balls.add(ball);
    }


}
