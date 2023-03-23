package com.mygdx.game.desktop;


import com.mygdx.game.API;
import com.mygdx.game.sprites.PlayerData;

import java.util.ArrayList;

public class DesktopAPI implements API {

	@Override
	public void sendPos(PlayerData data) {
		System.out.println("aaaaaa");
	}

	@Override
	public void getMessage(ArrayList<String> messages) {
		System.out.println("...get");
	}
}
