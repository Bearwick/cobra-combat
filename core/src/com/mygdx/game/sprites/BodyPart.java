package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class BodyPart {
    private Vector2 position;
    private Sprite bodypart;

    public BodyPart(Texture texture, Vector2 position) {
        position = new Vector2(position);
        bodypart = new Sprite(texture);
        bodypart.setSize(MyGdxGame.GRID_CELL_X, MyGdxGame.GRID_CELL_Y);
    }
    public Vector2 getPosition(){
        return position;
    }

    public void setPosition(Vector2 position){
        this.position = position;
    }
    public Sprite getSprite(){
        return this.bodypart;
    }

}
