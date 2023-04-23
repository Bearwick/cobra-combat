package com.mygdx.game.states;

import static com.mygdx.game.MyGdxGame.API;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.LobbyData;
import com.mygdx.game.lobbyDataCallback;

public class WaitingForPlayersState extends State implements lobbyDataCallback {
    private final BitmapFont waitingMessage;
    private final Texture background;
    private float deltaTime;
    private final String lobbyName;
    private final Texture cancelBtn;
    private LobbyData lobbyData;
    private boolean joinGameNow;
    private final int cancelBtnOffset = 200;
    private final Vector3 touchPos;

    protected WaitingForPlayersState(GameStateManager gsm, String lobbyName) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("dirt.jpg");
        waitingMessage = new BitmapFont();
        waitingMessage.getData().setScale(9, 9);
        this.lobbyName = lobbyName;
        touchPos = new Vector3();
        lobbyData = new LobbyData();
        cancelBtn = new Texture("cancel.png");
        API.setApiCallback(this);
        joinGameNow = false;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos);

            System.out.println(touchPos.x + " - " + touchPos.y);
            if (touchPos.x > MyGdxGame.WIDTH / 2 - (cancelBtn.getWidth() / 2) && touchPos.x < MyGdxGame.WIDTH / 2 + (cancelBtn.getWidth() / 2))
                if (touchPos.y > cam.position.y - cancelBtnOffset - cancelBtn.getHeight() && touchPos.y < cam.position.y - cancelBtnOffset) {
                    API.deleteLobby("newLobby");
                    API.resetJoinGameBooleans();
                    gsm.set(new GameMenuState(gsm));
                }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
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
        System.out.println("This function should never be called through this state: WaitingForPlayersState");
    }


    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        waitingMessage.draw(sb, "Waiting for players.....", cam.position.x - MyGdxGame.GRID_CELL_X * 6, cam.position.y);
        sb.draw(cancelBtn, MyGdxGame.WIDTH / 2 - (cancelBtn.getWidth() / 2), cam.position.y - cancelBtnOffset - cancelBtn.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
