package com.mygdx.game;

import com.mygdx.game.data.LobbyData;
import com.mygdx.game.data.PlayerData;

import java.util.ArrayList;
import java.util.Map;

public interface API {
    void sendPos(PlayerData data);
    void getMessage(ArrayList<String> messages);

    //  ---------- Setters Game Lobby ----------
    void createNewLobby(String lobbyName, LobbyData data);

    Boolean joinLobby(String lobby);
    void deleteLobby(String lobby);

    boolean checkForNewPlayers(String lobby);

    //  ---------- Getters Game Lobby ----------
    void FindLobby();

}
