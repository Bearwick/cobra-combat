package com.mygdx.game.states;

import static com.mygdx.game.MyGdxGame.API;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.LobbyData;
import com.mygdx.game.lobbyDataCallback;

public class GameLobbyState extends State implements lobbyDataCallback {

    private static final int changeNameBtnOffset = -370;
    private final BitmapFont userName;
    private final MyTextInputListener listener = new MyTextInputListener();
    private final Texture background;
    private final Texture playBtn;
    private final Texture changeNameBtn;
    private final Vector3 touchPos;
    private LobbyData lobbyData;
    private final String lobbyName;
    private String playerName = "";
    private boolean joinGameNow;
    private boolean createGameNow;


    public GameLobbyState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("dirt.jpg");
        playBtn = new Texture("play.png");
        changeNameBtn = new Texture("changeNameBtn.png");
        touchPos = new Vector3();
        joinGameNow = false;
        createGameNow = false;

        this.userName = new BitmapFont();
        this.userName.getData().setScale(9, 9);

        this.lobbyData = new LobbyData();
        this.lobbyName = "newLobby";
        API.setApiCallback(this);
    }

    private void joinGameLobby() {
        API.FindLobby(playerName);
    }

    @Override
    public void joinGameCallback(LobbyData lobbyData) {
        System.out.println("Join Game Callback: Attempting to join lobby... ");
        this.lobbyData = lobbyData;
        joinGameNow = true;

    }

    @Override
    public void createGameCallback() {
        System.out.println("Create Game Callback:: Creating new lobby ");
        createGameNow = true;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos); // calibrates the input to your camera's dimensions

            //Change name button
            if (touchPos.x > (cam.position.x - (changeNameBtn.getWidth() / 2)) && touchPos.x < (cam.position.x + (changeNameBtn.getWidth() / 2))) {
                if (touchPos.y > (cam.position.y + changeNameBtnOffset) && touchPos.y < (cam.position.y + changeNameBtn.getHeight() + changeNameBtnOffset)) {
                    Gdx.input.getTextInput(listener, "Player name", "", "E.g. Bit snake");
                }
            }
            //Start game button
            if (touchPos.x > (cam.position.x - (playBtn.getWidth() / 2)) && touchPos.x < (cam.position.x + (playBtn.getWidth() / 2))) {
                if (touchPos.y > (cam.position.y - (playBtn.getHeight() / 2)) && touchPos.y < (cam.position.y + (playBtn.getHeight() / 2))) {
                    joinGameLobby();
                }
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        if (joinGameNow)
            gsm.set(new GamePlayState(gsm, false, lobbyData));
        else if (createGameNow)
            gsm.set(new WaitingForPlayersState(gsm, lobbyName));

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn, cam.position.x - (playBtn.getWidth() / 2), cam.position.y - (playBtn.getHeight() / 2));
        sb.draw(changeNameBtn, cam.position.x - (changeNameBtn.getWidth() / 2), cam.position.y + changeNameBtnOffset);
        this.userName.draw(sb, "Player name: " + playerName, cam.position.x - userName.getRegion().getRegionWidth() - playerName.length() * (userName.getRegion().getRegionWidth() / 6) - 70, cam.position.y + (playBtn.getHeight()) + userName.getRegion().getRegionHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        userName.dispose();
    }

    public class MyTextInputListener implements Input.TextInputListener {
        @Override
        public void input(String text) {
            playerName = text;
        }

        @Override
        public void canceled() {
        }
    }
}
