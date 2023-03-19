package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class BodyPart {
    private Vector2 position;
    private Sprite bodypart;

    public BodyPart(Texture texture, Vector2 position) {
        position = new Vector2(position);
        bodypart = new Sprite(texture);
    }
    public Vector2 getPosition(){
        return position;
    }
    public Sprite getBodyPart(){
        return this.bodypart;
    }

}
