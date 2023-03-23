package com.mygdx.game.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

public abstract class Snake {
    protected Texture bodyTexture;
    protected Array<BodyPart> body;
    private Sprite head;
    private Vector2 position;
    private float headRotation;
    protected Rectangle headBounds;

    public Snake(Texture headTexture,Texture bodyTexture){
        this.head = new Sprite(headTexture);
        this.bodyTexture = bodyTexture;
        this.body = new Array<BodyPart>();
        this.head.setSize(MyGdxGame.GRID_CELL_X, MyGdxGame.GRID_CELL_Y);
        this.head.setOriginCenter();
        this.position = new Vector2(0,0);
        this.body.add(new BodyPart(bodyTexture, new Vector2(position.x,position.y-MyGdxGame.GRID_CELL_Y),90));
        this.headBounds = new Rectangle();
        this.headBounds.setPosition(this.position);
        this.headBounds.setSize(MyGdxGame.GRID_CELL_X, MyGdxGame.GRID_CELL_Y);
    }
    public Array<BodyPart> getBody(){
        return this.body;
    }
    public Sprite getHead(){
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

    public void setHeadRotation(float degrees){
        this.head.setRotation(degrees);
        this.headRotation = Float.valueOf(degrees);
    }
    public float getHeadRotation(){
        return Float.valueOf(headRotation);
    }
    public abstract void move();

    public Rectangle getHeadBounds(){
        return new Rectangle(headBounds);
    }

    public abstract boolean collides();
}
