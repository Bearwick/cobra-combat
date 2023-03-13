package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	float deltaTime;
	int counter;
	API api;

	ArrayList<String> hei = new ArrayList<>();

	public MyGdxGame(API firebaseAPI) {
		this.api = firebaseAPI;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

		deltaTime += Gdx.graphics.getDeltaTime();

		if (deltaTime >= 5f) {
			counter++;
			deltaTime = deltaTime % 5 + 0;
			api.sendMessage(counter);
			api.getMessage(hei);

			if (hei.size() > 0) {
				for (String message: hei) {
					System.out.println(message);
				}
			}
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
