package com.snake.game.model;

import com.badlogic.gdx.Gdx;
import com.snake.game.model.entities.BallModel;
import com.snake.game.model.entities.CoinModel;
import com.snake.game.model.entities.EntityModel;
import com.snake.game.model.entities.NumberModel;
import com.snake.game.model.entities.SnakeModel;
import com.snake.game.model.entities.SquareModel;
import com.snake.game.model.entities.WallModel;

import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.parser.TokenType.CASE;

public class GameModel {

    private static GameModel instance;
    private SnakeModel snake;
    public int directionSnake=1;
    private List<BallModel> balls;
    public List<BallModel> snakeBalls;
    private List<SquareModel> squares;
    private List<CoinModel> coins;
    private List<WallModel> walls;

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
        walls=new ArrayList<WallModel>();
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

    public List<WallModel> getWalls() {
        return walls;
    }

    public List<CoinModel> getCoins(){
        return coins;
    }
    public void createSquare (float x, float y, int value,EntityModel.ModelType model) {
        SquareModel square;
        square = new SquareModel(x, y, 0,value, model);
        calculateNumbers(square);
        squares.add(square);


    }

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

    private void whichNumber(SquareModel square, int i,float x) {
        switch (i) {
            case 0:
                square.numbers.add(new NumberModel(x, square.getY(), 0, 0, EntityModel.ModelType.ZERO));
                break;
            case 1:
                square.numbers.add(new NumberModel(x, square.getY(), 0, 0, EntityModel.ModelType.ONE));
                break;
            case 2:
                square.numbers.add(new NumberModel(x, square.getY(), 0, 0, EntityModel.ModelType.TWO));
                break;
            case 3:
                square.numbers.add(new NumberModel(x, square.getY(), 0, 0, EntityModel.ModelType.THREE));
                break;
            case 4:
                square.numbers.add(new NumberModel(x, square.getY(), 0, 0, EntityModel.ModelType.FOUR));
                break;
            case 5:
                square.numbers.add(new NumberModel(x, square.getY(), 0, 0, EntityModel.ModelType.FIVE));
                break;
            case 6:
                square.numbers.add(new NumberModel(x, square.getY(), 0, 0, EntityModel.ModelType.SIX));
                break;

            case 7:
                square.numbers.add(new NumberModel(x, square.getY(), 0, 0, EntityModel.ModelType.SEVEN));
                break;
            case 8:
                square.numbers.add(new NumberModel(x, square.getY(), 0, 0, EntityModel.ModelType.EIGHT));
                break;
            case 9:
                square.numbers.add(new NumberModel(x, square.getY(), 0, 0, EntityModel.ModelType.NINE));
                break;


        }

    }

    public void addBallToSnake(int i){
        BallModel lastBall= snakeBalls.get(snakeBalls.size()-1);
        BallModel ball = new BallModel(lastBall.getX()+1,lastBall.getY()-0.1f,0,0);
        snakeBalls.add(ball);
    }
    public void updateSnakeWithInput(float x){
        float lastX=snakeBalls.get(0).getX();
        float lastY=snakeBalls.get(0).getY();
        float currX,currY;
        for (int i =1;i<snakeBalls.size();i++){
            currX=snakeBalls.get(i).getX();
            currY=snakeBalls.get(i).getY();
            //System.out.println("ball::"+i+" ::: "+currX+" ::: "+currY);
            //System.out.println("LastBall::"+i+" ::: "+lastX+" ::: "+lastY);
            snakeBalls.get(i).setX(lastX+directionSnake*0.8f);
           snakeBalls.get(i).setY(lastY-0.2f);
            lastX=currX;
            lastY=currY;


        }
        snakeBalls.get(0).setX(x);
        snakeBalls.get(0).setY(snakeBalls.get(0).getY());
        //System.out.println(snakeBalls.get(0).getY());
    }
    public void updateSnakeWithoutInput(){
        float lastX=snakeBalls.get(0).getX();
        float lastY=snakeBalls.get(0).getY();
        float currX,currY;
        for (int i =1;i<snakeBalls.size();i++){
            snakeBalls.get(i).setX(lastX);
            snakeBalls.get(i).setY(lastY-i);

        }
        snakeBalls.get(0).setY(snakeBalls.get(0).getY());

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

    public void createWall(float x, float y){
        WallModel wall;
        wall=new WallModel(x,y,0,0);
        walls.add(wall);
    }

    public void createCoin(float x, float y) {
        CoinModel coin;
        coin =new CoinModel(x,y,0,0);
        coins.add(coin);
    }

    public static void restart(){
        instance = null;
    }
}
