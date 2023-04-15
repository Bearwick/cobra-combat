package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import static com.mygdx.game.MyGdxGame.API;

public class WaitingForPlayersState extends State{
    private Texture background;
    BitmapFont waitingMessage;
    private float deltaTime;
    private String lobbyName;
    protected WaitingForPlayersState(GameStateManager gsm, String lobbyName) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("dirt.jpg");
        waitingMessage = new BitmapFont();
        waitingMessage.getData().setScale(MyGdxGame.GRID_CELL_Y/5, MyGdxGame.GRID_CELL_Y/5);
        this.lobbyName = lobbyName;
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        deltaTime += dt;
        if (deltaTime >= MyGdxGame.GAMESPEED) {
            deltaTime = deltaTime % MyGdxGame.GAMESPEED;

            // True when another player joins the game!
            if(API.checkForNewPlayers(lobbyName)){
                gsm.set(new GamePlayState(gsm, true));
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        waitingMessage.draw(sb, "Waiting for players.....", cam.position.x - MyGdxGame.GRID_CELL_X*11  , cam.position.y );
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
