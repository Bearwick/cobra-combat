package com.mygdx.game.desktop;


import com.mygdx.game.API;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.LobbyData;
import com.mygdx.game.data.PlayerData;
import com.mygdx.game.lobbyDataCallback;
import com.mygdx.game.oponentDataCallback;

import java.util.ArrayList;
import java.util.Map;

public class DesktopAPI implements API {
	public lobbyDataCallback apiCallback;

	@Override
	public void sendPos(PlayerData data) {
		//System.out.println("x: "+MyGdxGame.GRID_CELL_X+" - y:" + MyGdxGame.GRID_CELL_Y);
		System.out.println(data.toString());
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
		LobbyData lobbyData = new LobbyData();
		lobbyData.setPlayer1("DesktopPlayer1");
		lobbyData.setPlayer2("hei<3");
		//apiCallback.joinGameCallback(lobbyData);
		apiCallback.createGameCallback();
	}

	@Override
	public void setApiCallback(lobbyDataCallback apiCallback) {
		this.apiCallback = apiCallback;

	}

	@Override
	public void setGameCallback(oponentDataCallback getOponentData) {

	}

	@Override
	public void getOponentData(String oponentName) {

	}

	@Override
	public void resetJoinGameBooleans(){

	}
	@Override
	public void deletePlayerData(String playerName){

	}

}
