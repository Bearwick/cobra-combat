package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;

public class GameMenuState extends State{

    private Texture background;
    private Texture playBtn;
    private Texture tutorialBtn;
    private Texture customizeBtn;
    private Texture ccText;
    private Vector3 touchPos;

    public GameMenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("cobraCombatBG.png");
        playBtn = new Texture("play.png");
        tutorialBtn = new Texture("tutorial.png");
        customizeBtn = new Texture("customize.png");
        ccText = new Texture("ccText.png");
        touchPos = new Vector3();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            touchPos.set(Gdx.input.getX(),Gdx.input.getY(), 0);
            cam.unproject(touchPos); // calibrates the input to your camera's dimentions

            //If PLAY button is clicked:
            if(touchPos.x > (cam.position.x - (playBtn.getWidth()/2)) && touchPos.x < (cam.position.x + (playBtn.getWidth()/2)))
                if (touchPos.y >(cam.position.y+50) && touchPos.y < (cam.position.y+50+playBtn.getHeight())){
                    gsm.set(new GamePlayState(gsm));
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
        sb.draw(playBtn, cam.position.x - (playBtn.getWidth()/2), cam.position.y+50);
        sb.draw(customizeBtn, MyGdxGame.WIDTH/2 - (customizeBtn.getWidth()/2),  cam.position.y-250);
        sb.draw(tutorialBtn, MyGdxGame.WIDTH/2 - (tutorialBtn.getWidth()/2),  cam.position.y - 550);

        sb.draw(ccText, MyGdxGame.WIDTH/2 - (ccText.getWidth()/2), MyGdxGame.HEIGHT - 160);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        tutorialBtn.dispose();
        customizeBtn.dispose();
        ccText.dispose();
    }
}

