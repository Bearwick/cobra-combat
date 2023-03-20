package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public interface Edible {

    public void setPosition(Vector2 position);
    public Vector2 getPosition();
    public Sprite getBody();

    public void dispose();

}
