package com.mygdx.game.snakes;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

public abstract class Snake {
    protected Texture bodyTexture;
    protected Array<BodyPart> body;
    protected Rectangle headBounds;
    private final Sprite head;
    private Vector2 position;
    private float headRotation;
    private int score;

    public Snake(Texture headTexture, Texture bodyTexture, Vector2 startingPosition) {
        this.position = startingPosition;
        this.head = new Sprite(headTexture);
        this.bodyTexture = bodyTexture;
        this.body = new Array<>();
        this.head.setSize(MyGdxGame.GRID_CELL_X, MyGdxGame.GRID_CELL_Y);
        this.head.setOriginCenter();
        this.head.setPosition(position.x, position.y);
        this.body.add(new BodyPart(bodyTexture, new Vector2(position.x, position.y - MyGdxGame.GRID_CELL_Y), 90));
        this.headBounds = new Rectangle();
        this.headBounds.setPosition(this.position);
        this.headBounds.setSize(MyGdxGame.GRID_CELL_X, MyGdxGame.GRID_CELL_Y);
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore() {
        this.score++;
    }

    public Array<BodyPart> getBody() {
        return this.body;
    }

    public Sprite getHead() {
        return this.head;
    }

    public Vector2 getHeadPosition() {
        return new Vector2(this.position);
    }

    public void setHeadPosition(Vector2 position) {
        this.position = position;
        this.head.setPosition(this.position.x, this.position.y);
        this.headBounds.setPosition(this.position);
    }

    public float getHeadRotation() {
        return headRotation;
    }

    public void setHeadRotation(float degrees) {
        this.head.setRotation(degrees);
        this.headRotation = degrees;
    }
}
