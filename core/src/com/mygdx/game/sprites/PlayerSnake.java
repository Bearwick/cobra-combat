package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class PlayerSnake extends Snake {
    private String direction;
    public Vector2 lastTailPosition;
    private PlayerData playerData;
    public PlayerSnake(Texture headTexture, Texture bodyTexture) {
        super(headTexture, bodyTexture);
        direction = MyGdxGame.dir_up;

        // Creates object for player data such as position. Used for Firebase.
        setPlayerData();
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
        // After the snake has moved, the position in playerData gets changed.


        /*NOTE TURNED OFF BECAUSE COULD NOT HANDLE GROWING SNAKE*/
        //updatePositionalData();
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

    public PlayerData getPlayerData() {
        return playerData;
    }

    /**
     * Creates a new playerData object and inserts the following data into it:
     *  - The player's nickname / username
     *  - the positions of every segment of the snake-object
     *  For updating the positions of the segments, use `updatePositionalData`
     */
    public void setPlayerData() {
        playerData = new PlayerData();
        playerData.setNickName("[INSERT NICKNAME HERE]");
        // Adds the entries for the body segments into the positions list
        // For updating said positions, use
        playerData.addPosition(this.getHeadPosition());
        for (BodyPart body : this.getBody()) {
            playerData.addPosition(body.getPosition());
        }
    }

    /**
     * Updates the positional data for a player snake.
     *
     * || Warning ||: playerData.changePosition only *modifies* the positions
     *  of existing body segments and can thus throw and error if one tries to
     *  update the position after getting a new body segment (see comment below)
     */

    public void updatePositionalData() {
        playerData.changePosition(0, this.getHeadPosition());
        for (int i=0; i<this.getBody().size-1; i++) {
            playerData.changePosition(i, this.getBody().get(i).getPosition());
        }
    }

    // TODO: add data logic for when a snake gains/loses body parts.
    //  Potentially in the unimplemented `addBodyPart` and `popBodyPart`.

}
