package com.snake.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;

public class NumberModel extends EntityModel {
    int value;
    public static final int WIDTH = 35;
    public static final int HEIGHT = 60;
    public float time_to_decrement = 1f;
    public Body body;
    public boolean toRemove = false;
    public ModelType modelType;

    public NumberModel(float x, float y, float rotation, int value, ModelType model) {
        super(x, y, rotation);
        this.value = value;
        this.modelType = model;


    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    @Override
    public ModelType getType() {
        return modelType;
    }

    public void update(float delta, float speed) {
        setY(getY() - speed * delta);
        if (getY() < 0)
            toRemove = true;
    }
}