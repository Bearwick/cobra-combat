package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class PlayerSnake extends Snake {

    private String direction;
    public Vector2 lastTailPosition;
    public PlayerSnake(Texture headTexture, Texture bodyTexture) {
        super(headTexture, bodyTexture);
        direction = MyGdxGame.dir_up;
    }

    @Override
    public void move() {
        moveBody();
        // sprite.translate can be used to move slowly to next position!!!
        if (this.direction == MyGdxGame.dir_up){
            setPosition(getHeadPosition().add(0,MyGdxGame.GRID_CELL_Y));
            setHeadRotation(180);
        } else if (this.direction == MyGdxGame.dir_down) {
            setPosition(getHeadPosition().add(0,-MyGdxGame.GRID_CELL_Y));
            setHeadRotation(0);
        } else if (this.direction == MyGdxGame.dir_left) {
            setPosition(getHeadPosition().add(-MyGdxGame.GRID_CELL_X,0));
            setHeadRotation(270);
        }else if(this.direction == MyGdxGame.dir_right){
            setPosition(getHeadPosition().add(MyGdxGame.GRID_CELL_X,0));
            setHeadRotation(90);
        }

    }

    private void moveBody(){
        lastTailPosition = getBody().get(getBody().size-1).getPosition();
        for (int i = getBody().size-1; i >= 0; i-- ){
            if (i == 0){
                //Set the position of the first bodypart, equal to the head position.
                getBody().get(i).setPosition(getHeadPosition());
            }else {
                //Going from tail to head, set each part position to the position of the part in front of it.
                getBody().get(i).setPosition(getBody().get(i-1).getPosition());
            }
        }
    }

    @Override
    public boolean collides() {
        return false;
    }
    public Vector2 getLastTailPosition(){
        return lastTailPosition;
    }
    public void setDirection(String direction){
        this.direction = direction;
    }
}
