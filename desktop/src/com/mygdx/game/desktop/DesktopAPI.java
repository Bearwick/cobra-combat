package com.mygdx.game.desktop;


import com.mygdx.game.API;

import java.util.ArrayList;

public class DesktopAPI implements API {

	@Override
	public void sendMessage(int i) {
		System.out.println("...send");
	}

	@Override
	public void getMessage(ArrayList<String> messages) {
		System.out.println("...get");
	}
}
