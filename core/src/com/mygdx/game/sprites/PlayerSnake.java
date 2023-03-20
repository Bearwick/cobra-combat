package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class PlayerSnake extends Snake {

    private String direction;
    public PlayerSnake(Texture headTexture, Texture bodyTexture) {
        super(headTexture, bodyTexture);
        direction = MyGdxGame.dir_up;
    }

    @Override
    public void move() {
        // translate can be used to move slowly to next position!!!
        if (this.direction == MyGdxGame.dir_up){
            setPosition(getPosition().add(0,MyGdxGame.GRID_CELL_Y));
            setHeadRotation(180);

        } else if (this.direction == MyGdxGame.dir_down) {
            setPosition(getPosition().add(0,-MyGdxGame.GRID_CELL_Y));
            setHeadRotation(0);

        } else if (this.direction == MyGdxGame.dir_left) {
            setPosition(getPosition().add(-MyGdxGame.GRID_CELL_X,0));
            setHeadRotation(270);

        }else if(this.direction == MyGdxGame.dir_right){
            setPosition(getPosition().add(MyGdxGame.GRID_CELL_X,0));
            setHeadRotation(90);


        }
    }

    @Override
    public boolean collides() {
        return false;
    }

    public void setDirection(String direction){
        this.direction = direction;
    }
}
