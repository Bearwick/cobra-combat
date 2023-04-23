package com.mygdx.game.snakes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class BodyPart {
    private Vector2 position;
    private final Sprite bodyPart;
    private float rotation;
    private final Rectangle bodyBounds;

    public BodyPart(Texture texture, Vector2 position, float rotation) {
        this.position = new Vector2(position);
        this.bodyPart = new Sprite(texture);
        this.bodyPart.setSize(MyGdxGame.GRID_CELL_X, MyGdxGame.GRID_CELL_Y);
        this.bodyPart.setOriginCenter();
        this.bodyPart.setPosition(position.x, position.y);
        this.rotation = rotation;
        this.bodyPart.setRotation(rotation);
        this.bodyBounds = new Rectangle();
        this.bodyBounds.setPosition(this.position);
        this.bodyBounds.setSize(MyGdxGame.GRID_CELL_X, MyGdxGame.GRID_CELL_Y);

    }

    public Vector2 getPosition() {
        return new Vector2(position);
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        this.bodyPart.setPosition(this.position.x, this.position.y);
        this.bodyBounds.setPosition(this.position);
    }

    public Sprite getSprite() {
        return new Sprite(this.bodyPart);
    }

    public float getRotation() {
        return this.rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
        this.bodyPart.setRotation(rotation);
    }

    public Rectangle getBounds() {
        return new Rectangle(bodyBounds);
    }
}
