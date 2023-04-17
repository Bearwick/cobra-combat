package com.mygdx.game.states;

import static com.mygdx.game.MyGdxGame.API;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.LobbyData;
import com.mygdx.game.data.PlayerData;
import com.mygdx.game.oponentDataCallback;
import com.mygdx.game.sprites.BodyPart;
import com.mygdx.game.sprites.Edible;
import com.mygdx.game.sprites.EdibleFactory;
import com.mygdx.game.sprites.OpponentSnake;
import com.mygdx.game.sprites.PlayerSnake;

import java.util.ArrayList;
import java.util.HashMap;

public class GamePlayState extends State implements oponentDataCallback {
    private Texture background;
    private ShapeRenderer shapeRenderer;
    private PlayerSnake player;
    private OpponentSnake opponent;
    private Texture leftBtn;
    private Texture rightBtn;
    private Vector2 startingPosition;
    private Vector2 oponentStartingPosition;

    private Vector3 touchPos;
    private float deltaTime;
    private float deltaTime2;
    private float deltaTime3;
    EdibleFactory edibleFactory;
    ArrayList<Edible> edibleArray;

    private LobbyData lobbyData;
    private PlayerData oponentData;
    private String playerName;
    private String oponentName;
    protected GamePlayState(GameStateManager gsm, Boolean isPlayer1, LobbyData lobbyData) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("dirt.jpg");
        //shapeRenderer = new ShapeRenderer();
        edibleFactory = new EdibleFactory();
        this.lobbyData = lobbyData;
        for (int i=0; i<5; i++){
            edibleFactory.getEdible("APPLE");
        }
        for (int i=0; i<2; i++){
            edibleFactory.getEdible("RAINBOW");
        }
        edibleArray = edibleFactory.getEdibleArray();
        touchPos = new Vector3();
        oponentData = new PlayerData();

        if (isPlayer1){
            startingPosition = new Vector2(0,0);
            oponentStartingPosition = new Vector2(MyGdxGame.GRID_CELL_X *(MyGdxGame.CELL_RATIO),0);
            playerName = lobbyData.getPlayer1();
            oponentName = lobbyData.getPlayer2();
        }
        else {
            startingPosition = new Vector2(MyGdxGame.GRID_CELL_X *(MyGdxGame.CELL_RATIO),0);
            oponentStartingPosition = new Vector2(0,0);
            playerName = lobbyData.getPlayer2();
            oponentName = lobbyData.getPlayer1();
        }

        if (GameCustomizeState.getCustomSnake() == 2) {
            player = new PlayerSnake(new Texture("snakehead2.png"), new Texture("snakebody2.png"),startingPosition, playerName);
            opponent = new OpponentSnake(new Texture("snakehead.png"), new Texture("snakebody.png"),oponentStartingPosition, oponentName);
        }
        else {
            player = new PlayerSnake(new Texture("snakehead.png"), new Texture("snakebody.png"),startingPosition, playerName);
            opponent = new OpponentSnake(new Texture("snakehead2.png"), new Texture("snakebody2.png"),oponentStartingPosition, oponentName);
        }
        API.setGameCallback(this);

    }

    @Override
    protected void handleInput() {

        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos); // calibrates the input to your camera's dimensions

            if (touchPos.x < cam.position.x) {  //Press left side of screen to move 90 degrees counter-clockwise
                if(player.getDirection() == "up") {
                    player.setDirection(MyGdxGame.dir_left);
                }
                else if (player.getDirection() == "right"){
                    player.setDirection(MyGdxGame.dir_up);
                }
                else if (player.getDirection() == "down"){
                    player.setDirection(MyGdxGame.dir_right);
                }
                else if(player.getDirection() == "left"){
                    player.setDirection(MyGdxGame.dir_down);
                }

            }

            if (touchPos.x > cam.position.x) { //Press right side of screen to move 90 degrees clock-wise
                if(player.getDirection() == "up") {
                    player.setDirection(MyGdxGame.dir_right);
                }
                else if (player.getDirection() == "right"){
                    player.setDirection(MyGdxGame.dir_down);
                }
                else if (player.getDirection() == "down"){
                    player.setDirection(MyGdxGame.dir_left);
                }

                else if(player.getDirection() == "left"){
                    player.setDirection(MyGdxGame.dir_up);
                }
            }
        }

        //Remember to remove this before delivery!! This is only for desktop testing!
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) player.setDirection(MyGdxGame.dir_up);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) player.setDirection(MyGdxGame.dir_down);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.setDirection(MyGdxGame.dir_right);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.setDirection(MyGdxGame.dir_left);

    }

    @Override
    public void update(float dt) {
        handleInput();
        deltaTime += dt;
        deltaTime2 += dt;
        deltaTime3 += dt;
        if (player.hasEaten(edibleArray)) {
            if (edibleFactory.getType().equals("APPLE")) {
                deltaTime2 = deltaTime2 % MyGdxGame.GAMESPEED * 4;
                player.addBodyPart(player.getLastTailPosition());
                edibleFactory.getEdible("APPLE");
            }
            else { //if type is RAINBOW
                deltaTime2 = deltaTime2 % MyGdxGame.GAMESPEED * 4;
                for (int i = 0; i < 2; i++) {
                    player.addBodyPart(player.getLastTailPosition());
                }
                edibleFactory.getEdible("RAINBOW");
            }
        }

        if (player.collides())
            gsm.set(new GameOverState(gsm));
        if (deltaTime >= MyGdxGame.GAMESPEED) {
            deltaTime = deltaTime % MyGdxGame.GAMESPEED;
            player.move();
            API.sendPos(player.getPlayerData());
            API.getOponentData(oponentName);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0);

        for (Edible e: edibleArray){
            e.getBody().draw(sb);
        }

        for(BodyPart bodypart: player.getBody()){
            bodypart.getSprite().draw(sb);
        }
        player.getHead().draw(sb);

        for (BodyPart bodyPart: opponent.getBody())
            bodyPart.getSprite().draw(sb);
        opponent.getHead().draw(sb);

        sb.end();
        //drawGrid();

    }

    private void drawGrid(){ //Helper function to draw see-through grid, copy-pasted.
        shapeRenderer.setProjectionMatrix(cam.projection);
        shapeRenderer.setTransformMatrix(cam.view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int x = 0; x < MyGdxGame.WIDTH; x += MyGdxGame.GRID_CELL_X){
            for (int y = 0; y < MyGdxGame.HEIGHT; y += MyGdxGame.GRID_CELL_Y){
                shapeRenderer.setColor(109f/255f, 255f/255f, 109f/255f, 1.0f);
                shapeRenderer.rect(x, y, MyGdxGame.GRID_CELL_X, MyGdxGame.GRID_CELL_Y);
            }
        }
        shapeRenderer.end();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void setOponentData(PlayerData opponentData) {
        System.out.println("setOponentData : Callback");
        oponentData = opponentData;
        if(oponentData == null)
            System.out.println("oponentData is NULL !!");
        else {
            System.out.println(oponentData);
            opponent.setOpponentData(opponentData);
        }


    }
}
