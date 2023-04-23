package com.mygdx.game;

import com.mygdx.game.data.PlayerData;

public interface API {
    void sendPos(PlayerData data);

    //  ---------- Setters Game Lobby ----------
    void createNewLobby(String lobbyName, String playerName);

    void joinLobby(String name);
    void deleteLobby(String lobby);

    void checkForNewPlayers(String lobby);

    //  ---------- Getters Game Lobby ----------
    void findLobby(String playerName);

    void resetJoinGameBooleans();
    void setApiCallback(lobbyDataCallback getLobbyData);
    void setGameCallback(opponentDataCallback getOponentData);

    void getOpponentData(String opponentName);

    void deletePlayerData(String playerName);

}
