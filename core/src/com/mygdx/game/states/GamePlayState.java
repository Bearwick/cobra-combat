package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.BodyPart;
import com.mygdx.game.sprites.PlayerSnake;

public class GamePlayState extends State {
    private Texture background;
    private ShapeRenderer shapeRenderer;
    private PlayerSnake player;
    private float deltaTime;
    float deltaTime2;

    protected GamePlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("dirt.jpg");
        shapeRenderer = new ShapeRenderer();

        if (GameCustomizeState.getCustomSnake() == 2) {
            player = new PlayerSnake(new Texture("snakehead2.png"), new Texture("snakebody2.png"));
        }
        else {
            player = new PlayerSnake(new Texture("snakehead.png"), new Texture("snakebody.png"));
        }
    }

    @Override
    protected void handleInput() {
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
        if (deltaTime >= MyGdxGame.GAMESPEED) {
            deltaTime = deltaTime % MyGdxGame.GAMESPEED;

            player.move();
            this.gsm.getApi().sendPos(player.getPlayerData());
        }
        if (deltaTime2 >= MyGdxGame.GAMESPEED*4) {
            deltaTime2 = deltaTime2 % MyGdxGame.GAMESPEED*4;
            player.addBodyPart(player.getLastTailPosition());
        }
        if (player.collides())
            gsm.set(new GameOverState(gsm));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0);

        for(BodyPart bodypart: player.getBody()){
            bodypart.getSprite().draw(sb);
        }
        player.getHead().draw(sb);
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
}
