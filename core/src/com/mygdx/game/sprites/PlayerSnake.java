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
            moveBody();
            setPosition(getPosition().add(0,MyGdxGame.GRID_CELL_Y));
            setHeadRotation(180);

        } else if (this.direction == MyGdxGame.dir_down) {
            moveBody();
            setPosition(getPosition().add(0,-MyGdxGame.GRID_CELL_Y));
            setHeadRotation(0);

        } else if (this.direction == MyGdxGame.dir_left) {
            moveBody();
            setPosition(getPosition().add(-MyGdxGame.GRID_CELL_X,0));
            setHeadRotation(270);

        }else if(this.direction == MyGdxGame.dir_right){
            moveBody();
            setPosition(getPosition().add(MyGdxGame.GRID_CELL_X,0));
            setHeadRotation(90);
        }
    }

    private void moveBody(){
        for (int i = getBody().size-1; i >= 0; i-- ){

            if (i == 0){
                //Set the position of the first bodypart, equal to the head position.
                getBody().get(i).getSprite().setPosition(getPosition().x, getPosition().y);
            }else {
                getBody().get(i).getSprite().setPosition(getBody().get(i-1).getPosition().x,getBody().get(i-1).getPosition().y);
            }

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
