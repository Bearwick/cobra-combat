package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.states.GameMenuState;
import com.mygdx.game.states.GameStateManager;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {

	public static final int WIDTH = 3120;
	public static final int HEIGHT = 1440;
	public static final int GRID_CELL_X = WIDTH/32;
	public static final int GRID_CELL_Y = HEIGHT/16;
	public static final float GAMESPEED = 0.5f;

	public static final String dir_left = "left";
	public static final String dir_up = "up";
	public static final String dir_right = "right";
	public static final String dir_down = "down";


	private GameStateManager gsm;
	SpriteBatch batch;

	float deltaTime;
	int counter;
	public static API api;

	ArrayList<String> hei = new ArrayList<>();

	public MyGdxGame(API firebaseAPI) {
		this.api = firebaseAPI;
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new GameMenuState(gsm));
	}


	@Override
	public void render() {
/*
		deltaTime += Gdx.graphics.getDeltaTime();
		if (deltaTime >= 5f) {
			counter++;
			deltaTime = deltaTime % 5 + 0;
			api.sendMessage(counter);
			api.getMessage(hei);

			if (hei.size() > 0) {
				for (String message : hei) {
					System.out.println(message);
				}
			}
		}
*/
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
