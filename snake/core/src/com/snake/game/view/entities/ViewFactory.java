package com.snake.game.view.entities;

import com.snake.game.SnakeSmash;
import com.snake.game.model.entities.EntityModel;

import java.util.HashMap;
import java.util.Map;
public class ViewFactory {
    private static Map<EntityModel.ModelType, EntityView> cache =
            new HashMap<EntityModel.ModelType, EntityView>();

    public static EntityView makeView(SnakeSmash game, EntityModel model) {
        if (!cache.containsKey(model.getType())) {
            if (model.getType() == EntityModel.ModelType.SNAKE)
                cache.put(model.getType(), new SnakeView(game));
            if (model.getType() == EntityModel.ModelType.BALL)
                cache.put(model.getType(), new BallView(game));
            if (model.getType() == EntityModel.ModelType.COIN)
                cache.put(model.getType(), new CoinView(game));
            if (model.getType() == EntityModel.ModelType.SQUARE)
                cache.put(model.getType(), new SquareView(game));
            if (model.getType() == EntityModel.ModelType.WALL)
                cache.put(model.getType(), new WallView(game));
        }
        return cache.get(model.getType());
    }
}
