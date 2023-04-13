package com.mygdx.game.states;

import static com.mygdx.game.MyGdxGame.API;

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
    private Texture playBtn;
    private Texture changeNameBtn;
    private Vector3 touchPos;
    private ArrayList<Boolean> isLoading;

    
    
    private boolean hasGamelobby = false;
    private String GameLobbyRef;
    private Map<String, Boolean> lobbies;
    private boolean playerReady = false;
    private static int playBtnOffset =50;
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
        lobbies = new HashMap<>();
        isLoading = new ArrayList<Boolean>();
        isLoading.add(0, true);
        isLoading.add(1, false);

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






    public void createGameSession() {
        if (playerReady && !hasGamelobby) {
            API.getLobbies(lobbies, isLoading);

            if (!isLoading.get(0)) {

            if (findOpenGameLobby()) {
                System.out.println("GameLobbyState: Found open game lobby. Joining lobby: " + GameLobbyRef);
                joinGameLobby();
            } else {
                System.out.println("GameLobbyState: No open game lobbies. Creating new!");
                createGameLobby();
            }
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
        isLoading.set(1, true);
        API.createNewLobby(playerName, isLoading);

        if (!isLoading.get(1)) {
            hasGamelobby = true;
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos); // calibrates the input to your camera's dimensions

            if (touchPos.x > (cam.position.x - (changeNameBtn.getWidth() / 2)) && touchPos.x < (cam.position.x+(changeNameBtn.getWidth() / 2))) {
                if(touchPos.y > (cam.position.y + changeNameBtnOffset) && touchPos.y < (cam.position.y + changeNameBtn.getHeight() + changeNameBtnOffset)) {
                    Gdx.input.getTextInput(listener, "Display name", "", "E.g. Bit snake");
                    System.out.println("text");
                }
            }


            if (touchPos.x > (cam.position.x - (playBtn.getWidth() / 2)) && touchPos.x < (cam.position.x + (playBtn.getWidth() / 2))) {
                if (touchPos.y > (cam.position.y - (playBtn.getHeight() / 2)) && touchPos.y < (cam.position.y + (playBtn.getHeight() / 2))) {
                    playerReady = true;
                    System.out.println("1");
                    gsm.set(new GamePlayState(gsm));
                }
            }

        }
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
        sb.draw(playBtn, cam.position.x - (playBtn.getWidth() / 2), cam.position.y - (playBtn.getHeight() / 2));
        sb.draw(changeNameBtn, cam.position.x - (changeNameBtn.getWidth() / 2), cam.position.y +changeNameBtnOffset);
        this.userName.draw(sb, "Player name: " + playerName, cam.position.x -userName.getRegion().getRegionWidth()- playerName.length()*(userName.getRegion().getRegionWidth()/6)-70 , cam.position.y  + (playBtn.getHeight()) + userName.getRegion().getRegionHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        userName.dispose();
    }
}
