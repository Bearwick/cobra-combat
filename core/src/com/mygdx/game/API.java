package com.mygdx.game;

import com.mygdx.game.sprites.PlayerData;

import java.util.ArrayList;

public interface API {
    void sendPos(PlayerData data);
    void getMessage(ArrayList<String> messages);
}
