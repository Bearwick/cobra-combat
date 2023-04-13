package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;

public class GameTutorialState extends State{

    private Texture background;
    private Vector3 touchPos;
    private Texture tut1;
    private Texture tut2;
    private Texture tut3;
    private Texture tut4;
    private static int tutOffset = -650;
    private static int page;
    protected GameTutorialState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("cobraCombatBG.png");
        tut1 = new Texture("tutorial1.png");
        tut2 = new Texture("tutorial2.png");
        tut3 = new Texture("tutorial3.png");
        tut4 = new Texture("tutorial4.png");
        touchPos = new Vector3();
        page = 1;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            page += 1;
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0);
        if (page == 1) {
            sb.draw(tut1, cam.position.x - (tut1.getWidth() / 2), cam.position.y + tutOffset);
        } else if (page == 2) {
            sb.draw(tut2, cam.position.x - (tut2.getWidth() / 2), cam.position.y + tutOffset);
        } else if (page == 3) {
            sb.draw(tut3, cam.position.x - (tut3.getWidth() / 2), cam.position.y + tutOffset);
        } else if (page == 4) {
            sb.draw(tut4, cam.position.x - (tut4.getWidth() / 2), cam.position.y + tutOffset);
        } else {
            gsm.set(new GameMenuState(gsm));
        }
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        tut1.dispose();
        tut2.dispose();
        tut3.dispose();
        tut4.dispose();
    }
}
