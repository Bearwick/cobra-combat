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
        moveBody(); // Body must be moved before the head.
        if (this.direction == MyGdxGame.dir_up){
            setHeadPosition(getHeadPosition().add(0,MyGdxGame.GRID_CELL_Y));
            setHeadRotation(180);
        } else if (this.direction == MyGdxGame.dir_down) {
            setHeadPosition(getHeadPosition().add(0,-MyGdxGame.GRID_CELL_Y));
            setHeadRotation(0);
        } else if (this.direction == MyGdxGame.dir_left) {
            setHeadPosition(getHeadPosition().add(-MyGdxGame.GRID_CELL_X,0));
            setHeadRotation(270);
        }else if(this.direction == MyGdxGame.dir_right){
            setHeadPosition(getHeadPosition().add(MyGdxGame.GRID_CELL_X,0));
            setHeadRotation(90);
        }
    }

    private void moveBody(){
        // Used as a "ghost" tile for when a new body segment is added to the snake
        lastTailPosition = getBody().get(getBody().size-1).getPosition();

        //Going from tail to body closest to head.... so "else" comes first
        for (int i = getBody().size-1; i >= 0; i-- ){
            if (i == 0){
                // Set the position of the first bodypart, equal to the head position.
                getBody().get(i).setPosition(getHeadPosition());
                getBody().get(i).setRotation(getHeadRotation());
            }else {
                // set each part position to the position of the part in front of it.
                getBody().get(i).setPosition(getBody().get(i-1).getPosition());
                getBody().get(i).setRotation(getBody().get(i-1).getRotation());
            }
        }

        // After the snake has moved, the position in playerData gets changed.
        updatePositionalData();
    }

    public void addBodyPart(Vector2 lastTailPosition){
        //Add a body segment at the tail, with same rotation as the tail.
        body.add(new BodyPart(bodyTexture, lastTailPosition,body.get(body.size-1).getRotation()));
        // Notify the playerData-object that a new tail has been added
        playerData.addPosition(lastTailPosition);
    }
    public Vector2 getLastTailPosition(){
        return new Vector2(lastTailPosition);
    }
    public void setDirection(String direction){
        this.direction = direction;
    }
    @Override
    public boolean collides() {
        System.out.println("pos: ");
        System.out.println(getHeadPosition().x);
        System.out.println(getHeadPosition().y);
        if (getHeadPosition().x < 0 || getHeadPosition().x > (MyGdxGame.WIDTH-MyGdxGame.GRID_CELL_X))
            return true;
        if (getHeadPosition().y < 0 || getHeadPosition().y > (MyGdxGame.HEIGHT-MyGdxGame.GRID_CELL_Y))
            return true;
        return false;
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
     */
    public void updatePositionalData() {
        playerData.changePosition(0, this.getHeadPosition());
        for (int i=0; i<this.getBody().size-1; i++) {
            playerData.changePosition(i, this.getBody().get(i).getPosition());
        }
    }
}
