package com.mygdx.game.snakes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.data.PlayerData;

import java.util.List;

public class OpponentSnake extends Snake {

    private final Texture bodyTexture;
    private Boolean dead;

    public OpponentSnake(Texture headTexture, Texture bodyTexture, Vector2 startingPosition) {
        super(headTexture, bodyTexture, startingPosition);
        this.bodyTexture = bodyTexture;
        this.dead = false;
    }

    public void setDead(Boolean dead) {
        this.dead = dead;
    }

    public Boolean isDead() {
        return this.dead;
    }

    /**
     * Creates a new playerData object and inserts the following data into it:
     * - The player's nickname / username
     * - the positions of every segment of the snake-object
     * For updating the positions of the segments, use `updatePositionalData`
     */
    public void setOpponentData(PlayerData opponentData) {
        List<Vector2> pos = opponentData.getPosition();
        List<Float> rot = opponentData.getRotation();

        setDead(opponentData.getDead());

        int totalSnakeSize = pos.size();
        this.setHeadPosition(pos.get(0));
        this.setHeadRotation(rot.get(0));

        while (this.getBody().size < totalSnakeSize - 1)
            this.getBody().add(new BodyPart(bodyTexture, pos.get(totalSnakeSize - 1), rot.get(totalSnakeSize - 1)));
        System.out.println(totalSnakeSize);
        setScore(totalSnakeSize - 2);
        for (int i = 1; i < totalSnakeSize; i++) {
            this.getBody().get(i - 1).setPosition(pos.get(i));
            this.getBody().get(i - 1).setRotation(rot.get(i));
        }
    }
}

