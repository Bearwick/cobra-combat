package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.PlayerData;

import java.util.ArrayList;

public class PlayerSnake extends Snake {
    private String direction;
    private int length;
    public Vector2 lastTailPosition;
    public Float lastTailRotation;
    private PlayerData playerData;
    public PlayerSnake(Texture headTexture, Texture bodyTexture, Vector2 startingPosition, String nickName) {
        super(headTexture, bodyTexture, startingPosition);
        direction = MyGdxGame.dir_up;
        // Creates object for player data such as position. Used for Firebase.
        setPlayerData(nickName);
        length = 2;
    }
    public int getLength() {
        return length;
    }
    @Override
    public void move() {
        moveBody();
        rotateBody();
        // After the snake has moved, the position in playerData gets changed.
        updatePositionalData();
    }

    private void moveBody(){
        // Used as a "ghost" tile for when a new body segment is added to the snake
        lastTailPosition = getBody().get(getBody().size-1).getPosition();
        lastTailRotation = getBody().get(getBody().size-1).getRotation();

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
    }
    public void rotateBody(){
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
    public void addBodyPart(Vector2 lastTailPosition){
        //Add a body segment at the tail, with same rotation as the tail.
        body.add(new BodyPart(bodyTexture, lastTailPosition,body.get(body.size-1).getRotation()));
        // Notify the playerData-object that a new tail has been added
        playerData.addPosition(lastTailPosition);
        playerData.addRotation(lastTailRotation);
        length += 1;
        System.out.println(Integer.toString(length));
    }
    public Vector2 getLastTailPosition(){
        return new Vector2(lastTailPosition);
    }
    public void setDirection(String direction){
        this.direction = direction;
    }
    @Override
    public boolean collides() {
        if (getHeadPosition().x < 0 || getHeadPosition().x > (MyGdxGame.WIDTH-MyGdxGame.GRID_CELL_X))
            return true;
        if (getHeadPosition().y < 0 || getHeadPosition().y > (MyGdxGame.HEIGHT-MyGdxGame.GRID_CELL_Y))
            return true;
        for(BodyPart bodypart : body){
            if (headBounds.overlaps(bodypart.getBounds()))
                return true;
        }
        return false;
    }

    public boolean hasEaten(ArrayList<Edible> edibleArray){
        for(Edible e: edibleArray){
            if (headBounds.overlaps(e.getBounds())){
                edibleArray.remove(e);
                return true;
            }
    }
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
    public void setPlayerData(String nickname) {
        playerData = new PlayerData();
        playerData.setNickName(nickname);
        // Adds the entries for the body segments into the positions list
        // For updating said positions, use
        playerData.addPosition(this.getHeadPosition());
        playerData.addRotation(this.getHeadRotation());
        for (BodyPart body : this.getBody()) {
            playerData.addPosition(body.getPosition());
            playerData.addRotation(body.getRotation());
        }
    }

    /**
     * Updates the positional data for a player snake.
     */
    public void updatePositionalData() {
        //System.out.println(this.getHeadPosition());
        playerData.changePosition(0, this.getHeadPosition());
        playerData.changeRotation(0, this.getHeadRotation());

        for (int i=0; i<this.getBody().size; i++) {
            //System.out.println(i);
            //System.out.println(this.getBody().get(i).getPosition());
            playerData.changePosition(i+1, this.getBody().get(i).getPosition());
            playerData.changeRotation(i+1, this.getBody().get(i).getRotation());
        }
        System.out.println("");
    }

    public String getDirection(){
        return direction;
    }
}
