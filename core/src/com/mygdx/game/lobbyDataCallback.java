package com.mygdx.game;

import com.mygdx.game.data.LobbyData;

public interface lobbyDataCallback{
    void joinGameCallback(LobbyData lobbyData);
    void createGameCallback();

}
