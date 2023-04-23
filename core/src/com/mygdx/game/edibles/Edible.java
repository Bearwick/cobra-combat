package com.mygdx.game.edibles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public interface Edible {

    Vector2 getPosition();

    void setPosition(Vector2 position);

    Sprite getBody();

    Rectangle getBounds();

    String getType();

    void setType(String type);

    void dispose();

}
