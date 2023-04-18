package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.PlayerData;

import java.util.List;

public class OpponentSnake extends Snake {

    protected Texture bodyTexture;
    private PlayerData opponentData;
    private int opponentLength;

    public OpponentSnake(Texture headTexture, Texture bodyTexture, Vector2 startingPosition, String nickName) {
        super(headTexture, bodyTexture, startingPosition);
        this.bodyTexture = bodyTexture;
        opponentLength = 2;

    }
    public PlayerData getPlayerData() {
        return opponentData;
    }

    /**
     * Creates a new playerData object and inserts the following data into it:
     * - The player's nickname / username
     * - the positions of every segment of the snake-object
     * For updating the positions of the segments, use `updatePositionalData`
     */
    public void setOpponentData(PlayerData opponentData) {
        this.opponentData = opponentData;
        List<Vector2> pos = opponentData.getPosition();
        List<Float> rot = opponentData.getRotation();
        int totalSnakesize = pos.size();

        this.setHeadPosition(pos.get(0));
        this.setHeadRotation(rot.get(0));

        while (this.getBody().size < totalSnakesize-1)
            this.getBody().add(new BodyPart(bodyTexture, pos.get(totalSnakesize-1), rot.get(totalSnakesize-1)));
        System.out.println(totalSnakesize);
        opponentLength = totalSnakesize;
        for(int i = 1; i<totalSnakesize; i++){
            this.getBody().get(i-1).setPosition(pos.get(i));
            this.getBody().get(i-1).setRotation(rot.get(i));
        }
    }

    public int getOpponentLength() {
        return opponentLength;
    }
}

