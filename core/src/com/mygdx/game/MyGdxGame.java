package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameMenuState;
import com.mygdx.game.states.GameStateManager;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {

	public static final int WIDTH = 3120;
	public static final int HEIGHT = 1440;
	public static final int CELL_RATIO = 64;
	public static final int GRID_CELL_X = WIDTH/CELL_RATIO;
	public static final int GRID_CELL_Y = HEIGHT/(CELL_RATIO/2);
	public static final float GAMESPEED = 0.6f;

	public static final String dir_left = "left";
	public static final String dir_up = "up";
	public static final String dir_right = "right";
	public static final String dir_down = "down";


	private GameStateManager gsm;

	private SpriteBatch batch;
	public static API API;
	


	public MyGdxGame(API firebaseAPI) {
		this.API = firebaseAPI;
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		gsm = new GameStateManager(this.API);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new GameMenuState(gsm));
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
