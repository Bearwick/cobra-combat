package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MyGdxGame;

public class GamePlayState extends State {
    private Texture background;
    private ShapeRenderer shapeRenderer;
    private static final int GRID_CELL_X = MyGdxGame.WIDTH/32;
    private static final int GRID_CELL_Y = MyGdxGame.HEIGHT/16;

    protected GamePlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("cobraCombatBG.png");
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    protected void handleInput() {

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
        sb.end();
        drawGrid();
    }
    private void drawGrid(){
        shapeRenderer.setProjectionMatrix(cam.projection);
        shapeRenderer.setTransformMatrix(cam.view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int x = 0; x < MyGdxGame.WIDTH; x += GRID_CELL_X){
            for (int y = 0; y < MyGdxGame.HEIGHT; y += GRID_CELL_Y){
                shapeRenderer.setColor(109f/255f, 255f/255f, 109f/255f, 1.0f);
                shapeRenderer.rect(x, y, GRID_CELL_X, GRID_CELL_Y);
            }
        }
        shapeRenderer.end();
    }
    @Override
    public void dispose() {

    }
}
