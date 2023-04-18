package com.mygdx.game.states;

import static com.mygdx.game.MyGdxGame.API;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;

public class VictoryState extends State {

    private Texture background;
    private Texture title;
    private static int titleOffset = -400;
    private Texture menuBtn;
    private static int menuBtnOffset = -250;
    private Vector3 touchPos;

    protected VictoryState(GameStateManager gsm, String playerName) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("cobraCombatBG.png");
        title = new Texture("victory.png");
        menuBtn = new Texture("menu.png");
        touchPos = new Vector3();
        API.resetJoinGameBooleans();
        API.deletePlayerData(playerName);
    }
    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos);

            if(touchPos.x > MyGdxGame.WIDTH/2 - (menuBtn.getWidth()/2) && touchPos.x < MyGdxGame.WIDTH/2 + (menuBtn.getWidth()/2))
                if (touchPos.y > cam.position.y + menuBtnOffset && touchPos.y < cam.position.y + menuBtnOffset + menuBtn.getHeight()){
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
        sb.draw(menuBtn, MyGdxGame.WIDTH/2 - (menuBtn.getWidth()/2),  cam.position.y + menuBtnOffset);
        sb.draw(title, MyGdxGame.WIDTH/2 - (title.getWidth()/2), MyGdxGame.HEIGHT +titleOffset);
        sb.end();
    }

    @Override
    public void dispose() {
        title.dispose();
        background.dispose();
    }
}
