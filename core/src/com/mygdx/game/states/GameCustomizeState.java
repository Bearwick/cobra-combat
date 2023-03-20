package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;

public class GameCustomizeState extends State{

    private Texture background;
    private Texture chooseBtn;
    private static int chooseBtnOffset =-550;
    private Vector3 touchPos;


    protected GameCustomizeState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("cobraCombatBG.png");
        chooseBtn = new Texture("chooseSkin.png");
        touchPos = new Vector3();
    }

    @Override
    protected void handleInput() {

        //If choose button ic clicked return to main menu
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos);

            if (touchPos.x > (MyGdxGame.WIDTH / 2 - (chooseBtn.getWidth() / 2)) && touchPos.x < MyGdxGame.WIDTH / 2 + (chooseBtn.getWidth() / 2))
                if (touchPos.y > cam.position.y + chooseBtnOffset && touchPos.y < cam.position.y + chooseBtnOffset + chooseBtn.getHeight()) {
                    gsm.set(new GameMenuState(gsm));
                }
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
        sb.draw(chooseBtn, cam.position.x - (chooseBtn.getWidth()/2), cam.position.y+chooseBtnOffset);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        chooseBtn.dispose();
    }
}
