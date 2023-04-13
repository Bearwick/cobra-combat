package com.mygdx.game;

import com.mygdx.game.sprites.PlayerData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface API {
    void sendPos(PlayerData data);
    void getMessage(ArrayList<String> messages);

    //  ---------- Setters Game Lobby ----------
    void createNewLobby(String playerName, ArrayList<Boolean> isLoading);

    void updateLobby(String lobby);
    void addLobby(String lobby);
    void deleteLobby(String lobby);

    void updatePosition(String lobby, String displayName);

    void incrementScore(String lobby, String displayName);



    //  ---------- Getters Game Lobby ----------
    void getLobbies(Map<String, Boolean> lobbies, ArrayList<Boolean> isLoading);

    void getLobbyStatus(String lobby);

    void getOpponentDisplayName(String lobby);

    void getOpponentPosition(String lobby);
}
