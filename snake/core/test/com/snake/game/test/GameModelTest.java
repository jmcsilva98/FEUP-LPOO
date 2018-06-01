package com.snake.game.test;

import com.snake.game.model.GameModel;
import com.snake.game.model.entities.BallModel;
import com.snake.game.model.entities.CoinModel;
import com.snake.game.model.entities.EntityModel;
import com.snake.game.model.entities.SnakeModel;
import com.snake.game.model.entities.SquareModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.snake.game.model.entities.EntityModel.ModelType.BLUESQUARE;
import static com.snake.game.model.entities.EntityModel.ModelType.PINKSQUARE;
import static org.junit.Assert.*;

public class GameModelTest extends GameTest {

    @Test
    public void testGetSnake() {
        GameModel gameModel = GameModel.getInstance();
        SnakeModel snake = new SnakeModel(20,10,10);
        gameModel.setSnake(snake);
        assertEquals(snake, gameModel.getSnake());
    }

    @Test
    public void testGetBalls() {
        GameModel gameModel = GameModel.getInstance();
        List<BallModel> balls =  new ArrayList<BallModel>();
        gameModel.setBalls(balls);
        assertEquals(balls, gameModel.getBalls());
    }

    @Test
    public void testGetSquares() {
        GameModel gameModel = GameModel.getInstance();
        List<SquareModel> squares =  new ArrayList<SquareModel>();
        gameModel.setSquares(squares);
        assertEquals(squares, gameModel.getSquares());
    }

    @Test
    public void testGetCoins() {
        GameModel gameModel = GameModel.getInstance();
        List<CoinModel> coins =  new ArrayList<CoinModel>();
        gameModel.setCoins(coins);
        assertEquals(coins, gameModel.getCoins());
    }

    @Test
    public void testCreateSquare() {
        GameModel gameModel = GameModel.getInstance();
        SquareModel squareModel;
        List<SquareModel> squares =  new ArrayList<SquareModel>();
        float x = 2;
        float y = 2;
        int value = 10;
        EntityModel.ModelType  model = PINKSQUARE;
        squareModel = new SquareModel(x,y, value,model);

        gameModel.calculateNumbers(squareModel);
        squares.add(squareModel);
        gameModel.setSquares(squares);

        gameModel.createSquare(x,y,value,model);
        assertEquals(squares, gameModel.getSquares());

    }


    @Test
    public void testWhichNumber(){
        GameModel gameModel = GameModel.getInstance();
        SquareModel square = new SquareModel(2,2, 0,null);
        gameModel.whichNumber(square, 0, 2);
        assertEquals(square.numbers.get(0).modelType, EntityModel.ModelType.ZERO);
        gameModel.whichNumber(square, 1, 2);
        assertEquals(square.numbers.get(1).modelType, EntityModel.ModelType.ONE);
        gameModel.whichNumber(square, 2, 2);
        assertEquals(square.numbers.get(2).modelType, EntityModel.ModelType.TWO);
        gameModel.whichNumber(square, 3, 2);
        assertEquals(square.numbers.get(3).modelType, EntityModel.ModelType.THREE);
        gameModel.whichNumber(square, 4, 2);
        assertEquals(square.numbers.get(4).modelType, EntityModel.ModelType.FOUR);
        gameModel.whichNumber(square, 5, 2);
        assertEquals(square.numbers.get(5).modelType, EntityModel.ModelType.FIVE);
        gameModel.whichNumber(square, 6, 2);
        assertEquals(square.numbers.get(6).modelType, EntityModel.ModelType.SIX);
        gameModel.whichNumber(square, 7, 2);
        assertEquals(square.numbers.get(7).modelType, EntityModel.ModelType.SEVEN);
        gameModel.whichNumber(square, 8, 2);
        assertEquals(square.numbers.get(8).modelType, EntityModel.ModelType.EIGHT);
        gameModel.whichNumber(square, 9, 2);
        assertEquals(square.numbers.get(9).modelType, EntityModel.ModelType.NINE);



    }

    @Test
    public void tetDeleteBallToSnake() {
        GameModel gameModel = GameModel.getInstance();
        List<BallModel> snakeballs =  new ArrayList<BallModel>();
        gameModel.setSnakeBalls(snakeballs);
        BallModel ball = new BallModel(2,2, 2);
        gameModel.getSnakeBalls().add(ball);
        gameModel.deleteBallToSnake();
        assertEquals(0,gameModel.getSnakeBalls().size());

    }

    @Test
    public void testCreateBall() {
        float x = 2;
        float y = 3;
        int value = 12;
        BallModel ball = new BallModel(x,y, value);

        GameModel gameModel = GameModel.getInstance();
        GameModel gameModel2 = GameModel.getInstance();

        gameModel2.getBalls().add(ball);
        gameModel.createBall(x,y,value);

        assertEquals(gameModel.getBalls(), gameModel2.getBalls());
    }


    @Test
    public void testCreateCoin() {
        float x = 2;
        float y = 3;
        CoinModel coin = new CoinModel(x,y,0,0);

        GameModel gameModel = GameModel.getInstance();
        GameModel gameModel2 = GameModel.getInstance();

        gameModel2.getCoins().add(coin);
        gameModel.createCoin(x,y);

        assertEquals(gameModel.getCoins(), gameModel2.getCoins());
    }

    @Test
    public void testRestart() {
        GameModel gameModel = GameModel.getInstance();
        GameModel newGame = GameModel.getInstance();
        gameModel.restart();

        assertEquals(gameModel, newGame);
    }
}