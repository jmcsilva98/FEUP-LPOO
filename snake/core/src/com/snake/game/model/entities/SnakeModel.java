package com.snake.game.model.entities;


import com.snake.game.tools.CollisionDetect;

public class SnakeModel extends EntityModel {
    public static final int WIDTH =17;
    public static final int HEIGHT =17;
    private int size;
    CollisionDetect collision;

    public  SnakeModel(int size, float x, float y,float rotation){
        super(x,y,rotation);
        this.size=size;
        this.collision = new CollisionDetect(x, y, WIDTH, HEIGHT);

    }

    @Override
    public ModelType getType() {
        return ModelType.SNAKE;
    }
    public int getSize(){
        return size;
    }
    public void setSize(int size){
        this.size=size;
    }

    public void update(float delta){
        collision.move(getX(), getY());
    }
    public int updateSize(int value){
        this.size= this.size-value;

        return this.size;
    }

    public void addBalls(int value){
        this.size+=value;
    }

    public CollisionDetect getCollisionDetect() {
        return collision;
    }
}
