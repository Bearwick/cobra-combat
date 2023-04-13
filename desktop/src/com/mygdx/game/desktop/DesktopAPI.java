package com.mygdx.game.desktop;


import com.mygdx.game.API;
import com.mygdx.game.data.LobbyData;
import com.mygdx.game.data.PlayerData;

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
	public void createNewLobby(String lobbyName, LobbyData data) {

	}
	@Override
	public Boolean joinLobby(String lobby) {
		return true;
	}
	@Override
	public void deleteLobby(String lobby) {

	}
	@Override
	public boolean checkForNewPlayers(String lobby) {
		return true;
	}

	@Override
	public void FindLobby() {

	}




}
