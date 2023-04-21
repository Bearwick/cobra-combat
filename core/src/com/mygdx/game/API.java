package com.mygdx.game;

import com.mygdx.game.data.LobbyData;
import com.mygdx.game.data.PlayerData;

import java.util.ArrayList;
import java.util.Map;

public interface API {
    void sendPos(PlayerData data);

    //  ---------- Setters Game Lobby ----------
    void createNewLobby(String lobbyName, String playerName);

    void joinLobby(String name);
    void deleteLobby(String lobby);

    void checkForNewPlayers(String lobby);

    //  ---------- Getters Game Lobby ----------
    void FindLobby(String playerName);

    void resetJoinGameBooleans();
    void setApiCallback(lobbyDataCallback getLobbyData);
    void setGameCallback(oponentDataCallback getOponentData);

    void getOponentData(String oponentName);

    void deletePlayerData(String playerName);

}
