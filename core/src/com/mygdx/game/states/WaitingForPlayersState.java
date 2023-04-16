package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.LobbyData;
import com.mygdx.game.lobbyDataCallback;

import static com.mygdx.game.MyGdxGame.API;

import java.util.concurrent.Callable;

public class WaitingForPlayersState extends State implements lobbyDataCallback {
    private Texture background;
    BitmapFont waitingMessage;
    private float deltaTime;
    private String lobbyName;
    private LobbyData lobbyData;
    private boolean joinGameNow;
    protected WaitingForPlayersState(GameStateManager gsm, String lobbyName) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("dirt.jpg");
        waitingMessage = new BitmapFont();
        waitingMessage.getData().setScale(MyGdxGame.GRID_CELL_Y/5, MyGdxGame.GRID_CELL_Y/5);
        this.lobbyName = lobbyName;
        lobbyData= new LobbyData();
        API.setApiCallback(this);
        joinGameNow=false;
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        deltaTime += dt;
        if (deltaTime >= MyGdxGame.GAMESPEED) {
            deltaTime = deltaTime % MyGdxGame.GAMESPEED;
            API.checkForNewPlayers(lobbyName);
            API.resetJoinGameBooleans();
        }
        if (joinGameNow)
            gsm.set(new GamePlayState(gsm, true, lobbyData));
    }
    @Override
    public void joinGameCallback(LobbyData lobbyData) {
        System.out.println("Waiting for player - Join Game Callback: Attempting to join lobby... ");
        this.lobbyData = lobbyData;
        joinGameNow = true;


    }

    @Override
    public void createGameCallback() {
        System.out.println("The fact that this method is called is a huge wierd bug.");
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
