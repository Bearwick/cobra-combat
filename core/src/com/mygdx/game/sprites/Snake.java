package com.mygdx.game.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

public abstract class Snake {
    private Texture bodyTexture;
    private Sprite head;
    private Array<BodyPart> body;
    private Vector2 position;

    public Snake(Texture headTexture,Texture bodyTexture){
        this.head = new Sprite(headTexture);
        this.bodyTexture = bodyTexture;
        body = new Array<BodyPart>();

        head.setSize(MyGdxGame.GRID_CELL_X, MyGdxGame.GRID_CELL_Y);
        head.setOriginCenter();
        position = new Vector2(0,0);

        body.add(new BodyPart(bodyTexture, new Vector2(position.x,position.y-MyGdxGame.GRID_CELL_Y)));

    }

    public Array<BodyPart> getBody(){
        return body;
    }
    public Sprite getHead(){
        return head;
    }

    public void insertBodyPart(){
        body.insert(0, new BodyPart(bodyTexture, position));
    }
    public void addBodyPart(Vector2 lastTailPosition){
        //Krever en del mer logikk å legge til en hale på enden av slangen,
        // hvis den ikke skal ligge på samme posisjon som den forrige halen.
        //Men det krever at vi flytter hala elementer, når det er enklest å bare la dem stå stille og fjerne/legge til
        // for hver flytt.
        body.add(new BodyPart(bodyTexture, lastTailPosition));
    }
    public void popBodyPart(){
        body.pop();
    }

    public Vector2 getHeadPosition() {
        return new Vector2(position);
    }
    public void setPosition(Vector2 position) {
        this.position = position;
        this.head.setPosition(position.x, position.y);
    }
    public void setHeadFlip(boolean horizontal, boolean vertical){
        head.setFlip(horizontal, vertical);
    }
    public void setHeadRotation(float degrees){
        head.setRotation(degrees);

    }

    public abstract void move();

    public abstract boolean collides();
}
