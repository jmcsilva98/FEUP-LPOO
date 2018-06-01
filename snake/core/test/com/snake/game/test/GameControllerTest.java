package com.snake.game.test;



import com.snake.game.controller.GameController;
import com.snake.game.model.GameModel;
import com.snake.game.model.entities.BallModel;
import com.snake.game.model.entities.CoinModel;
import com.snake.game.model.entities.EntityModel;
import com.snake.game.model.entities.SnakeModel;
import com.snake.game.model.entities.SquareModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameControllerTest {

    @Test
    public void testGetInstance() {
        GameController gameController = GameController.getInstance();
        assertEquals(gameController,GameController.getInstance());
    }


    @Test
    public void testUpdateSquares() {
        GameController gameController = GameController.getInstance();
        GameModel gameModel = GameModel.getInstance();
        ArrayList<SquareModel> squaresToRemove = new ArrayList<SquareModel>();

        List<SquareModel> squares = gameModel.getSquares();
        for(SquareModel square : squares){
            square.toRemove = true;
            gameController.updateSquares(2);
            assertEquals(squaresToRemove.size(), 1);
        }


    }

    @Test
    public void testUpdateBalls() {
        GameController gameController = GameController.getInstance();
        GameModel gameModel = GameModel.getInstance();
        List<BallModel> balls = gameModel.getBalls();
        ArrayList<BallModel> ballsToRemove = new ArrayList<BallModel>();

        for(BallModel ball :balls){
            ball.toRemove = true;
            gameController.updateBalls(2);
            assertTrue(ballsToRemove.size() == 1);
        }
    }

    @Test
    public void testUpdateCoin() {
        GameController gameController = GameController.getInstance();
        GameModel gameModel = GameModel.getInstance();
        List<CoinModel> coins = gameModel.getCoins();
        ArrayList<CoinModel> coinsToRemove = new ArrayList<CoinModel>();

        for(CoinModel coin : coins){
            coin.toRemove = true;
            gameController.updateCoin(2);
            assertTrue(coinsToRemove.size() == 1);
        }
    }

    @Test
    public void testDetectCollisionSquare() {
        GameController gameController = GameController.getInstance();
        GameModel gameModel = GameModel.getInstance();
        ArrayList<SquareModel> squares = new ArrayList<SquareModel>();
        SquareModel square = new SquareModel(10,11,5, EntityModel.ModelType.BLUESQUARE);
        squares.add(square);
        gameModel.setSquares(squares);
        SnakeModel snake = new SnakeModel(1,10,10);
        gameModel.setSnake(snake);
        gameController.detectCollisionSquare(5);
        assertTrue(GameModel.getInstance().getSnake().collideWithSquare);
        gameModel.getSquares().get(0).setValue(0);
        gameController.detectCollisionSquare(5);
        assertEquals(GameController.getInstance().speed,GameController.getInstance().saveSpeed,0);
        assertFalse(GameModel.getInstance().getSnake().collideWithSquare);
    }

    @Test
    public void testDetectCollisionBalls() {
        GameController gameController = GameController.getInstance();
        GameModel gameModel = GameModel.getInstance();
        ArrayList<BallModel> balls = new ArrayList<BallModel>();
        BallModel ball = new BallModel(10,11,5);
        balls.add(ball);
        gameModel.setBalls(balls);
        SnakeModel snake = new SnakeModel(1,10,10.9f);
        gameModel.setSnake(snake);
        gameController.detectCollisionBalls();
        assertEquals(2,gameModel.getSnake().getSize());
        assertEquals(1,gameController.getBallsToRemove().size());
        assertEquals(0,gameModel.getBalls().size());

    }

    @Test
    public void testDetectCollisionCoins() {
        GameModel.restart();
        GameController.restart();
        GameController gameController = GameController.getInstance();
        GameModel gameModel = GameModel.getInstance();
        ArrayList<CoinModel> coins = new ArrayList <CoinModel>();
        CoinModel coin = new CoinModel(10,11,0,1);
        coins.add(coin);
        gameModel.setCoins(coins);
        SnakeModel snake = new SnakeModel(1,10,10.9f);
        gameModel.setSnake(snake);
        gameController.detectCollisionCoins();
        assertEquals(1,gameController.getCoins());
        assertEquals(5,gameController.getScore());
        assertTrue(gameController.catchCoin);
        assertEquals(0,gameModel.getCoins().size());
    }

    @Test
    public void testDecrementSquare(){
        GameController gameController = GameController.getInstance();
        GameModel gameModel = GameModel.getInstance();
        SquareModel square = new SquareModel(10,10,5, EntityModel.ModelType.BLUESQUARE);
        SnakeModel snake = new SnakeModel(1,10,10.9f);
        gameModel.setSnake(snake);
        gameController.decrementSquare(5,square);
        assertEquals(2,gameController.getScore());
        assertEquals(4, square.getValue());
        assertEquals(2, square.time_to_decrement,0);
        assertEquals(0,snake.getSize());
        assertTrue(gameController.gameOver);
    }

    @Test
    public void testRestart() {
        GameController gameController = GameController.getInstance();
        GameController newGame = GameController.getInstance();
        gameController.restart();

        assertEquals(gameController, newGame);
    }

    @Test
    public void testGetScore() {
        GameController gameController = GameController.getInstance();
        int score = 2;
        gameController.setScore(score);
        assertEquals(score, gameController.getScore());
    }

    @Test
    public void testGetCoins() {
        GameController gameController = GameController.getInstance();
        int coins = 2;
        gameController.setCoins(coins);
        assertEquals(coins, gameController.getCoins());

    }
}