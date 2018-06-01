package com.snake.game.test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.snake.game.controller.GameController;
import com.snake.game.model.GameModel;
import com.snake.game.model.entities.BallModel;
import com.snake.game.model.entities.CoinModel;
import com.snake.game.model.entities.EntityModel;
import com.snake.game.model.entities.SquareModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameControllerTest {

    @Test
    public void getInstance() {
        GameController gameController = GameController.getInstance();
        assertEquals(gameController,GameController.getInstance());
    }


    @Test
    public void shiftRight() {
        GameController gameController = GameController.getInstance();
        float x = 20;


    }

    @Test
    public void shiftLeft() {
    }

    @Test
    public void updateSquares() {
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
    public void updateBalls() {
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
    public void updateCoin() {
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
    public void detectCollisionSquare() {
        GameController gameController = GameController.getInstance();
        GameModel gameModel = GameModel.getInstance();
        ArrayList<SquareModel> squares = new ArrayList<SquareModel>();
        SquareModel square = new SquareModel(10,15,0,5, EntityModel.ModelType.BLUESQUARE);
        squares.add(square);
        gameModel.setSquares(squares);






    }

    @Test
    public void detectCollisionBalls() {
    }

    @Test
    public void detectCollisionCoins() {
    }

    @Test
    public void detectCollisionWalls() {
    }

    @Test
    public void restart() {
        GameController gameController = GameController.getInstance();
        GameController newGame = GameController.getInstance();
        gameController.restart();

        assertEquals(gameController, newGame);
    }

    @Test
    public void getScore() {
        GameController gameController = GameController.getInstance();
        int score = 2;
        gameController.setScore(score);
        assertEquals(score, gameController.getScore());
    }

    @Test
    public void getCoins() {
        GameController gameController = GameController.getInstance();
        int coins = 2;
        gameController.setCoins(coins);
        assertEquals(coins, gameController.getCoins());

    }
}