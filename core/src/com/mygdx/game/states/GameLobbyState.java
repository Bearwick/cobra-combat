package com.mygdx.game.states;

import static com.mygdx.game.MyGdxGame.API;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.LobbyData;
import com.mygdx.game.data.PlayerData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameLobbyState extends State {

    private Texture background;
    private Texture playBtn;
    private Texture changeNameBtn;
    private Vector3 touchPos;

    private LobbyData lobbyData;
    private String lobbyName;

    private static int changeNameBtnOffset =-370;
    private String playerName = "";
    BitmapFont userName;
    MyTextInputListener listener = new MyTextInputListener();



    public GameLobbyState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("dirt.jpg");
        playBtn = new Texture("play.png");
        changeNameBtn = new Texture("changeNameBtn.png");
        touchPos = new Vector3();

        this.userName = new BitmapFont();
        this.userName.getData().setScale(MyGdxGame.GRID_CELL_Y/5, MyGdxGame.GRID_CELL_Y/5);

        this.lobbyData = new LobbyData("none", "none");
        this.lobbyName = "newLobby";
    }



    public class MyTextInputListener implements Input.TextInputListener {
        @Override
        public void input (String text) {
            playerName = text;
            lobbyData.setPlayer1(text);
        }

        @Override
        public void canceled () {
        }
    }

    public void findGameSession() {
        joinGameLobby();
        //createGameLobby();


    }
    private void joinGameLobby() {
        System.out.println("GameLobbyState: Attempting to join lobby... ");
        if (playerName=="")
            lobbyData.setPlayer2("Player2");
        if (API.joinLobby(this.playerName)){
            gsm.set(new GamePlayState(gsm, false));
        }
    }
    private void createGameLobby() {
        System.out.println("GameLobbyState: Creating new lobby ");

        if (playerName==""){
            lobbyData.setPlayer1("Player1");
        }

        API.createNewLobby(lobbyName, lobbyData);
        gsm.set(new WaitingForPlayersState(gsm, lobbyName));
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos); // calibrates the input to your camera's dimensions

            //Change name button
            if (touchPos.x > (cam.position.x - (changeNameBtn.getWidth() / 2)) && touchPos.x < (cam.position.x+(changeNameBtn.getWidth() / 2))) {
                if(touchPos.y > (cam.position.y + changeNameBtnOffset) && touchPos.y < (cam.position.y + changeNameBtn.getHeight() + changeNameBtnOffset)) {
                    Gdx.input.getTextInput(listener, "Player name", "", "E.g. Bit snake");
                }
            }
            //Start game button
            if (touchPos.x > (cam.position.x - (playBtn.getWidth() / 2)) && touchPos.x < (cam.position.x + (playBtn.getWidth() / 2))) {
                if (touchPos.y > (cam.position.y - (playBtn.getHeight() / 2)) && touchPos.y < (cam.position.y + (playBtn.getHeight() / 2))) {
                    findGameSession();
                }
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
        sb.draw(background, 0, 0);
        sb.draw(playBtn, cam.position.x - (playBtn.getWidth() / 2), cam.position.y - (playBtn.getHeight() / 2));
        sb.draw(changeNameBtn, cam.position.x - (changeNameBtn.getWidth() / 2), cam.position.y +changeNameBtnOffset);
        this.userName.draw(sb, "Player name: " + playerName, cam.position.x - userName.getRegion().getRegionWidth() - playerName.length()*(userName.getRegion().getRegionWidth()/6) - 70 , cam.position.y  + (playBtn.getHeight()) + userName.getRegion().getRegionHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        userName.dispose();
    }
}
