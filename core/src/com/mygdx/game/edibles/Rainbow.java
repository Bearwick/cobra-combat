package com.mygdx.game.edibles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class Rainbow implements Edible {

    private final Vector2 position;
    private final Sprite body;
    private final Rectangle bounds;
    private String type;

    public Rainbow(Vector2 position) {
        this.body = new Sprite(new Texture("rainbow.png"));
        body.setSize(MyGdxGame.GRID_CELL_X, MyGdxGame.GRID_CELL_Y);
        this.position = position;
        body.setPosition(position.x, position.y);
        bounds = new Rectangle(position.x, position.y, body.getWidth(), body.getHeight());
        setType("RAINBOW");
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override //See comments in apple edible
    public void setPosition(Vector2 position) {
    }

    @Override
    public Sprite getBody() {
        return body;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }


    @Override
    public void dispose() {
    }
}
