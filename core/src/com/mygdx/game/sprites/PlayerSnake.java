package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class PlayerSnake extends Snake {
    private String direction;
    private PlayerData playerData;
    public PlayerSnake(Texture headTexture, Texture bodyTexture) {
        super(headTexture, bodyTexture);
        direction = MyGdxGame.dir_up;

        // Creates object for player data such as position. Used for Firebase.
        setPlayerData();
    }

    @Override
    public void move() {
        if (this.direction == MyGdxGame.dir_up){
            setPosition(getPosition().add(0,MyGdxGame.GRID_CELL_Y));

        } else if (this.direction == MyGdxGame.dir_down) {
            setPosition(getPosition().add(0,-MyGdxGame.GRID_CELL_Y));

        } else if (this.direction == MyGdxGame.dir_left) {
            setPosition(getPosition().add(-MyGdxGame.GRID_CELL_X,0));

        }else if(this.direction == MyGdxGame.dir_right){
            setPosition(getPosition().add(MyGdxGame.GRID_CELL_X,0));
        }
        // After the snake has moved, the position in playerData gets changed.
        updatePositionalData();
    }

    @Override
    public boolean collides() {
        return false;
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
        playerData.addPosition(this.getPosition());
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
        playerData.changePosition(0, this.getPosition());
        for (int i=1; i<this.getBody().size; i++) {
            playerData.changePosition(i, this.getBody().get(i).getPosition());
        }
    }

    // TODO: add data logic for when a snake gains/loses body parts.
    //  Potentially in the unimplemented `addBodyPart` and `popBodyPart`.

}
