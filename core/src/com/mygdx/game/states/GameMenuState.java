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
    private Texture title;
    private Vector3 touchPos;

    private static int playBtnOffset =50;
    private static int tutorialBtnOffset = -250;
    private static int customizeBtnOffset = -550;
    private static int titleOffset = -160;

    public GameMenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("cobraCombatBG.png");
        playBtn = new Texture("play.png");
        tutorialBtn = new Texture("tutorial.png");
        customizeBtn = new Texture("customize.png");
        title = new Texture("ccText.png");
        touchPos = new Vector3();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            touchPos.set(Gdx.input.getX(),Gdx.input.getY(), 0);
            cam.unproject(touchPos); // calibrates the input to your camera's dimensions

            //If PLAY button is clicked:
            if(touchPos.x > (cam.position.x - (playBtn.getWidth()/2)) && touchPos.x < (cam.position.x + (playBtn.getWidth()/2)))
                if (touchPos.y >(cam.position.y+playBtnOffset) && touchPos.y < (cam.position.y+playBtnOffset+playBtn.getHeight())){
                    gsm.set(new GamePlayState(gsm));
                }
            //if CUSTOMIZE button is clicked:
            if(touchPos.x > (MyGdxGame.WIDTH/2 - (customizeBtn.getWidth()/2)) && touchPos.x < MyGdxGame.WIDTH/2 + (customizeBtn.getWidth()/2))
                if (touchPos.y > cam.position.y+customizeBtnOffset && touchPos.y < cam.position.y+customizeBtnOffset+customizeBtn.getHeight()){
                    gsm.set(new GameCustomizeState(gsm));
                }
            //If TUTORIAL button is clicked:
            if(touchPos.x > MyGdxGame.WIDTH/2 - (tutorialBtn.getWidth()/2) && touchPos.x < MyGdxGame.WIDTH/2 + (tutorialBtn.getWidth()/2))
                if (touchPos.y > cam.position.y + tutorialBtnOffset && touchPos.y < cam.position.y + tutorialBtnOffset + tutorialBtn.getHeight()){
                    gsm.set(new GameTutorialState(gsm));
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
        sb.draw(playBtn, cam.position.x - (playBtn.getWidth()/2), cam.position.y+playBtnOffset);
        sb.draw(customizeBtn, MyGdxGame.WIDTH/2 - (customizeBtn.getWidth()/2),  cam.position.y+customizeBtnOffset);
        sb.draw(tutorialBtn, MyGdxGame.WIDTH/2 - (tutorialBtn.getWidth()/2),  cam.position.y + tutorialBtnOffset);

        sb.draw(title, MyGdxGame.WIDTH/2 - (title.getWidth()/2), MyGdxGame.HEIGHT +titleOffset);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        tutorialBtn.dispose();
        customizeBtn.dispose();
        title.dispose();
    }
}

