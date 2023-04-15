package com.mygdx.game.desktop;


import com.mygdx.game.API;
import com.mygdx.game.data.LobbyData;
import com.mygdx.game.data.PlayerData;
import com.mygdx.game.lobbyDataCallback;

import java.util.ArrayList;
import java.util.Map;

public class DesktopAPI implements API {

	@Override
	public void sendPos(PlayerData data) {
		System.out.println("aaaaaa");
	}

	@Override
	public void getMessage(ArrayList<String> messages) {
		System.out.println("...get");
	}

	@Override
	public void createNewLobby(String lobbyName, String playerName) {

	}

	@Override
	public void joinLobby(String name) {

	}


	@Override
	public void deleteLobby(String lobby) {

	}

	@Override
	public void checkForNewPlayers(String lobby) {

	}

	@Override
	public void FindLobby(String playerName) {

	}

	@Override
	public void setApiCallback(lobbyDataCallback getLobbyData) {

	}


}
