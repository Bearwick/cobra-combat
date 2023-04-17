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
    private Texture tut5;
    private Texture tut6;
    private static int tutOffset = -650;
    private static int arrowOffset = 1200;
    private static int page;
    private Texture left;
    private Texture right;
    protected GameTutorialState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("cobraCombatBG.png");
        tut1 = new Texture("tutorialClockwise.png");
        tut2 = new Texture("tutorialAnticlockwise.png");
        tut3 = new Texture("tutorialApples.png");
        tut4 = new Texture("tutorialPowerup.png");
        tut5 = new Texture("tutorialDeath.png");
        tut6 = new Texture("tutorialEnd.png");
        left = new Texture("leftArrow.png");
        right = new Texture("rightArrow.png");
        touchPos = new Vector3();
        page = 1;
    }

    @Override
    protected void handleInput() {

        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos);

            if (touchPos.x < (MyGdxGame.WIDTH / 2 - (-arrowOffset- right.getWidth())) && touchPos.x > MyGdxGame.WIDTH / 2 + (arrowOffset))
                if (touchPos.y > cam.position.y && touchPos.y < cam.position.y + right.getHeight()) {
                    System.out.println("Go right");
                        page += 1;
                }
            if (touchPos.x > (MyGdxGame.WIDTH / 2 - (arrowOffset)) && touchPos.x < MyGdxGame.WIDTH / 2 - (arrowOffset-left.getWidth()))
                if (touchPos.y > cam.position.y && touchPos.y < cam.position.y + left.getHeight()) {
                    System.out.println("Go left");
                        page -= 1;
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
        sb.draw(right, cam.position.x + arrowOffset, cam.position.y);
        sb.draw(left, cam.position.x - arrowOffset, cam.position.y);
        if (page == 1) {
            sb.draw(tut1, cam.position.x - (tut1.getWidth() / 2), cam.position.y + tutOffset);
        } else if (page == 2) {
            sb.draw(tut2, cam.position.x - (tut2.getWidth() / 2), cam.position.y + tutOffset);
        } else if (page == 3) {
            sb.draw(tut3, cam.position.x - (tut3.getWidth() / 2), cam.position.y + tutOffset);
        } else if (page == 4) {
            sb.draw(tut4, cam.position.x - (tut4.getWidth() / 2), cam.position.y + tutOffset);
        } else if (page == 5) {
            sb.draw(tut5, cam.position.x - (tut4.getWidth() / 2), cam.position.y + tutOffset);
        } else if (page == 6) {
            sb.draw(tut6, cam.position.x - (tut4.getWidth() / 2), cam.position.y + tutOffset);
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
        tut5.dispose();
        tut6.dispose();
    }
}
