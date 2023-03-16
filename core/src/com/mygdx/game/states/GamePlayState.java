package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GamePlayState extends State {
    private Texture background;

    protected GamePlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("cobraCombatBG.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
