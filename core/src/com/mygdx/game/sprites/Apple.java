package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class Apple implements Edible{

    private Vector2 position;
    private Sprite body;
    private Rectangle bounds;
    private String type;

    public Apple(Vector2 position){
        this.body = new Sprite(new Texture("apple.png"));
        body.setSize(MyGdxGame.GRID_CELL_X, MyGdxGame.GRID_CELL_Y);
        this.position = position;
        body.setPosition(position.x, position.y);
        bounds = new Rectangle(position.x, position.y, body.getWidth(), body.getHeight());
        setType("APPLE");
    }

    @Override   //Ikke i bruk per nå, men kanskje den kan brukes for å løse et tilfelle hvor to edibles spawner samme sted
    public void setPosition(Vector2 position) {
        this.position = position;
        body.setPosition(position.x, position.y);
    }


    @Override
    public Vector2 getPosition() {
        return position;
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
