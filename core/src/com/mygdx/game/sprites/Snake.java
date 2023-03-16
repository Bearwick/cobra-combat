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
        head.setSize(MyGdxGame.GRID_CELL_X, MyGdxGame.GRID_CELL_Y);
        this.bodyTexture = bodyTexture;
        body = new Array<BodyPart>();
        position = new Vector2(0,0);

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
    public void addBodyPart(String direction){
        //Krever en del mer logikk å legge til en hale på enden av slangen,
        // hvis den ikke skal ligge på samme posisjon som den forrige halen.
        //Men det krever at vi flytter hala elementer, når det er enklest å bare la dem stå stille og fjerne/legge til
        // for hver flytt.
        body.add(new BodyPart(bodyTexture, body.peek().getPosition().add(0,0)));
    }
    public void popBodyPart(){
        body.pop();
    }

    public Vector2 getPosition() {
        return position;
    }
    public void setPosition(Vector2 position) {
        this.position = position;
        head.setPosition(position.x, position.y);
    }

    public abstract void move();

    public abstract boolean collides();





}
