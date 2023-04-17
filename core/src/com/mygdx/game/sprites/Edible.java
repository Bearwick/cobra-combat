package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public interface Edible {

    public void setPosition(Vector2 position);
    public Vector2 getPosition();
    public Sprite getBody();
    public Rectangle getBounds();
    public String getType();
    public void setType(String type);

    public void dispose();

}
