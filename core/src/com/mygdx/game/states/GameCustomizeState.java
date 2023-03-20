package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;

public class GameCustomizeState extends State{

    private Texture background;
    private Texture chooseBtn;
    private static int chooseBtnOffset =-550;
    private Vector3 touchPos;
    private Texture snake1;
    private Texture snake2;
    private static int snakePlacement = 50;

    private static int snakeNumber;


    protected GameCustomizeState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("cobraCombatBG.png");
        chooseBtn = new Texture("chooseSkin.png");
        touchPos = new Vector3();
        snake1 = new Texture("snakehead.png");
        snake2 = new Texture(("snakehead2.png"));
        snakeNumber = 1;
    }

    public static int getCustomSnake() {
        return snakeNumber;
    }

    @Override
    protected void handleInput() {

        //Changes snake skin
        //Bruker sysout for å sjekke hvor mange ganger systemet registrerer at det blir trykket
        //Det kommer anpå hvor lenge man holder inne knappen
        //Antar at dette løser seg når vi bytter til touch skjerm
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            System.out.println("Go right");
            if (snakeNumber == 2) {
                snakeNumber = 1; }
            else {
                snakeNumber = 2; }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            System.out.println("Go left");
            if (snakeNumber == 2) {
                snakeNumber = 1; }
            else {
                snakeNumber = 2; }
        }

        //If choose button ic clicked return to main menu
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos);

            if (touchPos.x > (MyGdxGame.WIDTH / 2 - (chooseBtn.getWidth() / 2)) && touchPos.x < MyGdxGame.WIDTH / 2 + (chooseBtn.getWidth() / 2))
                if (touchPos.y > cam.position.y + chooseBtnOffset && touchPos.y < cam.position.y + chooseBtnOffset + chooseBtn.getHeight()) {
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
        sb.draw(chooseBtn, cam.position.x - (chooseBtn.getWidth()/2), cam.position.y+chooseBtnOffset);
        if (snakeNumber == 1) {
            sb.draw(snake1, cam.position.x - (snake1.getWidth() / 2), cam.position.y + snakePlacement);
        }
        else {
            sb.draw(snake2, cam.position.x - (snake2.getWidth() / 2), cam.position.y + snakePlacement);
        }
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        chooseBtn.dispose();
        snake1.dispose();
        snake2.dispose();
    }
}
