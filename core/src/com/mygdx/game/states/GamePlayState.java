package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.PlayerSnake;

public class GamePlayState extends State {
    private Texture background;
    private ShapeRenderer shapeRenderer;
    private float deltaTime;

    private static final int SNAKE_MOVEMENT_X = 32;
    private static final int SNAKE_MOVEMENT_Y = 16;

    private PlayerSnake player;

    protected GamePlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("dirt.jpg");
        shapeRenderer = new ShapeRenderer();

        player = new PlayerSnake(new Texture("snakehead.png"), new Texture("snakebody.png"));
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
        if (deltaTime >= 1f) {
            deltaTime = deltaTime % 1 + 0;

            player.move();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0);
        player.getHead().draw(sb);
        sb.end();
        drawGrid();
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
