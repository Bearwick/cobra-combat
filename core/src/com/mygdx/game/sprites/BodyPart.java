package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class BodyPart {
    private Vector2 position;
    private Sprite bodypart;
    private float rotation;

    public BodyPart(Texture texture, Vector2 position, float rotation) {
        this.position = new Vector2(position);
        this.bodypart = new Sprite(texture);
        this.bodypart.setSize(MyGdxGame.GRID_CELL_X, MyGdxGame.GRID_CELL_Y);
        this.bodypart.setOriginCenter();
        this.bodypart.setPosition(position.x, position.y);
        this.rotation=rotation;
        this.bodypart.setRotation(rotation);


    }
    public Vector2 getPosition(){
        return new Vector2(position);
    }

    public void setPosition(Vector2 position){
        this.position = position;
        this.bodypart.setPosition(position.x, position.y);
    }
    public Sprite getSprite(){
        return this.bodypart;
    }

    public float getRotation(){
        return Float.valueOf(this.rotation);
    }
    public void setRotation(float rotation){
        this.rotation = rotation;
        this.bodypart.setRotation(rotation);
    }
}
