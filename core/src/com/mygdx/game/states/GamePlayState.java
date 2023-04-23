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
import com.mygdx.game.edibles.Edible;
import com.mygdx.game.edibles.EdibleFactory;
import com.mygdx.game.opponentDataCallback;
import com.mygdx.game.snakes.BodyPart;
import com.mygdx.game.snakes.OpponentSnake;
import com.mygdx.game.snakes.PlayerSnake;

import java.util.ArrayList;
import java.util.Objects;

public class GamePlayState extends State implements opponentDataCallback {
    private final String opponentName;
    private final Texture background;
    private final PlayerSnake player;
    private final OpponentSnake opponent;
    private final Vector3 touchPos;
    private final EdibleFactory edibleFactory;
    private final ArrayList<Edible> edibleArray;
    private final String playerName;
    private final BitmapFont map;
    private float deltaTime;
    private float deltaTime2;
    private PlayerData opponentData;

    public GamePlayState(GameStateManager gsm, Boolean isPlayer1, LobbyData lobbyData) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("dirt.jpg");
        map = new BitmapFont();
        edibleFactory = new EdibleFactory();
        for (int i = 0; i < 5; i++) {
            edibleFactory.getEdible("APPLE");
        }
        for (int i = 0; i < 2; i++) {
            edibleFactory.getEdible("RAINBOW");
        }
        edibleArray = edibleFactory.getEdibleArray();
        touchPos = new Vector3();
        opponentData = new PlayerData();

        Vector2 opponentStartingPosition;
        Vector2 startingPosition;
        if (isPlayer1) {
            startingPosition = new Vector2(0, 0);
            opponentStartingPosition = new Vector2(MyGdxGame.GRID_CELL_X * (MyGdxGame.CELL_RATIO), 0);
            playerName = lobbyData.getPlayer1();
            opponentName = lobbyData.getPlayer2();
        } else {
            startingPosition = new Vector2(MyGdxGame.GRID_CELL_X * (MyGdxGame.CELL_RATIO - 1), 0);
            opponentStartingPosition = new Vector2(0, 0);
            playerName = lobbyData.getPlayer2();
            opponentName = lobbyData.getPlayer1();
        }

        if (GameCustomizeState.getCustomSnake() == 1) {
            player = new PlayerSnake(new Texture("snakehead.png"), new Texture("snakebody.png"), startingPosition, playerName);
            opponent = new OpponentSnake(new Texture("snakehead2.png"), new Texture("snakebody2.png"), opponentStartingPosition);
        } else if (GameCustomizeState.getCustomSnake() == 2) {
            player = new PlayerSnake(new Texture("snakehead2.png"), new Texture("snakebody2.png"), startingPosition, playerName);
            opponent = new OpponentSnake(new Texture("snakehead3.png"), new Texture("snakebody3.png"), opponentStartingPosition);
        } else {
            player = new PlayerSnake(new Texture("snakehead3.png"), new Texture("snakebody3.png"), startingPosition, playerName);
            opponent = new OpponentSnake(new Texture("snakehead.png"), new Texture("snakebody.png"), opponentStartingPosition);
        }
        API.setGameCallback(this);

    }

    @Override
    protected void handleInput() {

        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos); // calibrates the input to your camera's dimensions

            if (touchPos.x < cam.position.x) {  //Press left side of screen to move 90 degrees counter-clockwise
                if (Objects.equals(player.getDirection(), "up")) {
                    player.setDirection(MyGdxGame.dir_left);
                } else if (Objects.equals(player.getDirection(), "right")) {
                    player.setDirection(MyGdxGame.dir_up);
                } else if (Objects.equals(player.getDirection(), "down")) {
                    player.setDirection(MyGdxGame.dir_right);
                } else if (Objects.equals(player.getDirection(), "left")) {
                    player.setDirection(MyGdxGame.dir_down);
                }
            }

            if (touchPos.x > cam.position.x) { //Press right side of screen to move 90 degrees clock-wise
                if (Objects.equals(player.getDirection(), "up")) {
                    player.setDirection(MyGdxGame.dir_right);
                } else if (Objects.equals(player.getDirection(), "right")) {
                    player.setDirection(MyGdxGame.dir_down);
                } else if (Objects.equals(player.getDirection(), "down")) {
                    player.setDirection(MyGdxGame.dir_left);
                } else if (Objects.equals(player.getDirection(), "left")) {
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

        // Checks if the player overlaps with a pickup. If so, check to see which
        // pickup it is, apply whatever the pickup does, and spawn another one.
        Edible overlappingEdible = player.overlapsAnEdible(edibleArray);
        if (overlappingEdible != null) {
            System.out.println(overlappingEdible.getType());
            if (overlappingEdible.getType().equals("APPLE")) {
                deltaTime2 = deltaTime2 % MyGdxGame.GAMESPEED * 4;
                player.addBodyPart(player.getLastTailPosition());
                edibleFactory.getEdible("APPLE");
                System.out.println(("APPLE EDIBLE WAS EATEN"));
            } else if (overlappingEdible.getType().equals("RAINBOW")) { //if type is RAINBOW
                deltaTime2 = deltaTime2 % MyGdxGame.GAMESPEED * 4;
                // Adds three body segments to the snake
                for (int i = 0; i <= 2; i++) {
                    player.addBodyPart(player.getLastTailPosition());
                }
                edibleFactory.getEdible("RAINBOW");
                System.out.println("RAINBOW EDIBLE WAS EATEN");
            }
        }

        if (player.collides(opponent)) {
            player.setDead();
        }
        if (deltaTime >= MyGdxGame.GAMESPEED) {
            deltaTime = deltaTime % MyGdxGame.GAMESPEED;
            player.move();
            API.sendPos(player.getPlayerData());
            API.getOpponentData(opponentName);
            if (player.isDead())
                gsm.set(new GameOverState(gsm, playerName));
            if (opponent.isDead()) {
                gsm.set(new VictoryState(gsm, playerName));
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);

        for (Edible e : edibleArray) {
            e.getBody().draw(sb);
        }

        for (BodyPart bodypart : player.getBody()) {
            bodypart.getSprite().draw(sb);
        }
        player.getHead().draw(sb);

        for (BodyPart bodyPart : opponent.getBody())
            bodyPart.getSprite().draw(sb);
        opponent.getHead().draw(sb);

        String scoreBoard = playerName + ": " + player.getScore() + "\n" + opponentName + ": " + opponent.getScore();
        map.getData().setScale(5);
        map.draw(sb, scoreBoard, 100, 1300);

        sb.end();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void setOpponentData(PlayerData opponentData) {
        this.opponentData = opponentData;
        if (this.opponentData == null)
            System.out.println("opponentData is NULL !!");
        else {
            opponent.setOpponentData(opponentData);
        }


    }
}
