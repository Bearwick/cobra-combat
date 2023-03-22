package com.mygdx.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface API {
    void sendMessage(int i);
    void getMessage(ArrayList<String> messages);

    //  ---------- Setters Game Lobby ----------
    void createNewLobby();

    void updateLobby(String lobby);

    void updatePosition(String lobby, String displayName);

    void incrementScore(String lobby, String displayName);



    //  ---------- Getters Game Lobby ----------
    void getLobbies(Map<String, Boolean> lobbies);

    void getLobbyStatus(String lobby);

    void getOpponentDisplayName(String lobby);

    void getOpponentPosition(String lobby);
}
