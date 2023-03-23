package com.mygdx.game.desktop;


import com.mygdx.game.API;

import java.util.ArrayList;
import java.util.Map;

public class DesktopAPI implements API {

	@Override
	public void sendMessage(int i) {
		System.out.println("...send");
	}

	@Override
	public void getMessage(ArrayList<String> messages) {
		System.out.println("...get");
	}

	@Override
	public void createNewLobby(String playerName, ArrayList<Boolean> isLoading) {

	}

	@Override
	public void updateLobby(String lobby) {

	}

	@Override
	public void updatePosition(String lobby, String displayName) {

	}

	@Override
	public void incrementScore(String lobby, String displayName) {

	}

	@Override
	public void getLobbies(Map<String, Boolean> lobbies, ArrayList<Boolean> isLoading) {

	}
	@Override
	public void getLobbyStatus(String lobby) {

	}

	@Override
	public void getOpponentDisplayName(String lobby) {

	}

	@Override
	public void getOpponentPosition(String lobby) {

	}
}
