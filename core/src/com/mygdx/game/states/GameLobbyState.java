package com.mygdx.game.states;

import static com.mygdx.game.MyGdxGame.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameLobbyState extends State {

    private Texture background;
    private Texture startBtn;
    private Vector3 touchPos;

    private boolean hasGamelobby = false;
    private String GameLobbyRef;
    private Map<String, Boolean> lobbies;
    private boolean playerReady = false;
    private static int playBtnOffset =50;
    private String playerName = "";
    BitmapFont userName;
    MyTextInputListener listener = new MyTextInputListener();



    public GameLobbyState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("dirt.jpg");
        startBtn = new Texture("play.png");
        touchPos = new Vector3();
        this.userName = new BitmapFont();
        this.userName.getData().setScale(MyGdxGame.GRID_CELL_Y/15, MyGdxGame.GRID_CELL_Y/15);
        lobbies = new HashMap<>();


    }



    public class MyTextInputListener implements Input.TextInputListener {
        @Override
        public void input (String text) {
            playerName = text;
        }

        @Override
        public void canceled () {
        }
    }




    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos); // calibrates the input to your camera's dimensions

            if (touchPos.x > (cam.position.x - (startBtn.getWidth() / 2)) && touchPos.x < (cam.position.x + (startBtn.getWidth() / 2)) && touchPos.y > (cam.position.y + playBtnOffset + 100) && touchPos.y < (cam.position.y + playBtnOffset + 100 + startBtn.getHeight())) {
                    Gdx.input.getTextInput(listener, "Display name", "", "E.g. Bit snake");
                System.out.println("text");
            }


            else if (touchPos.x > (cam.position.x - (startBtn.getWidth() / 2)) && touchPos.x < (cam.position.x + (startBtn.getWidth() / 2))) {
                if (touchPos.y > (cam.position.y + playBtnOffset) && touchPos.y < (cam.position.y + startBtn.getHeight())) {
                    playerReady = true;
                    System.out.println("1");
                }
            }

        }
    }

    public void createGameSession() {
        if (playerReady && !hasGamelobby) {
            System.out.println("2");
            api.getLobbies(lobbies);
            System.out.println("3");


            if (findOpenGameLobby()) {
                System.out.println("GameLobbyState: Found open game lobby. Joining lobby: " + GameLobbyRef);
                joinGameLobby();
            } else {
                System.out.println("GameLobbyState: No open game lobbies. Creating new!");
                createGameLobby();
            }


        }

        else if (playerReady && hasGamelobby) {
            System.out.println("GameLobbyState: Starting game: Switching to GamePlayState.");
            gsm.set(new GamePlayState(gsm));
            playerReady = false;
        }
    }

    private boolean findOpenGameLobby() {

        for (Map.Entry<String, Boolean> entry : lobbies.entrySet()) {
            String lobbyKey = entry.getKey();
            Boolean lobbyValue = entry.getValue();
            // Do something with the key-value pair
            if (lobbyValue){
                GameLobbyRef = lobbyKey;
                return true;
            }
        }
        return false;
    }
    private void joinGameLobby() {
        System.out.println("GameLobbyState: Joining lobby: " + GameLobbyRef);
        hasGamelobby = true;
    }
    private void createGameLobby() {
        System.out.println("GameLobbyState: Creating new lobby ");
        hasGamelobby = true;
    }

    @Override
    public void update(float dt) {
        handleInput();
        createGameSession();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(startBtn, cam.position.x - (startBtn.getWidth() / 2), cam.position.y - (startBtn.getHeight() / 2));
        this.userName.draw(sb, "Display name: " + playerName, cam.position.x - (startBtn.getWidth()/2), cam.position.y  + 300);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        startBtn.dispose();
        userName.dispose();
    }
}
