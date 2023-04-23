package com.mygdx.game;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mygdx.game.data.LobbyData;
import com.mygdx.game.data.PlayerData;

import java.util.Objects;

public class FirebaseAPI implements API {
    public lobbyDataCallback apiCallback;
    public opponentDataCallback opponentCallback;
    private PlayerData opponentData;
    private final DatabaseReference lobbyRef;
    private final DatabaseReference gameRef;
    private Boolean gameJoined;
    private LobbyData lobbyData;

    public FirebaseAPI() {
        FirebaseDatabase rtdm = FirebaseDatabase.getInstance("https://cobra-combat-default-rtdb.europe-west1.firebasedatabase.app/");

        this.lobbyRef = rtdm.getReference("Lobbies");
        this.gameRef = rtdm.getReference("PlayerData");
        lobbyData = new LobbyData();
        opponentData = new PlayerData();
        gameJoined = false;
    }

    @Override
    public void sendPos(PlayerData data) {
        gameRef.child(data.getNickName()).setValue(data);
    }


    //  ---------- Setters Game Lobby ----------
    @Override
    public void createNewLobby(String lobbyName, String playerName) {
        if (!gameJoined) {
            DatabaseReference gameRef = lobbyRef.child(lobbyName);
            LobbyData data = new LobbyData();
            data.setPlayer1(playerName);
            gameRef.setValue(data);
            gameJoined = true;
            apiCallback.createGameCallback();
        }

    }

    //  ---------- Getters Game Lobby ----------
    @Override
    public void findLobby(String playerName) {
        joinLobby(playerName);
    }

    @Override
    public void deleteLobby(String lobby) {
        lobbyRef.child(lobby).removeValue();
    }

    @Override
    public void checkForNewPlayers(String lobbyName) {
        if (!gameJoined) {
            lobbyRef.child(lobbyName).get().addOnCompleteListener(task -> {
                lobbyData = task.getResult().getValue(LobbyData.class);
                if (lobbyData != null && !lobbyData.getPlayer2().equals("none")) {
                    gameJoined = true;
                    apiCallback.joinGameCallback(lobbyData);

                }
            });
        }
    }

    @Override
    public void joinLobby(String playerName) {
        if (!gameJoined) {
            lobbyRef.get().addOnCompleteListener(task -> {
                for (DataSnapshot ds : task.getResult().getChildren()) {
                    lobbyData = ds.getValue(LobbyData.class);

                    if (lobbyData != null && lobbyData.getPlayer2().equals("none") && !lobbyData.getPlayer1().equals("none")) {
                        lobbyData.setPlayer2(playerName);
                        DatabaseReference gameRef = lobbyRef.child(Objects.requireNonNull(ds.getKey()));
                        gameRef.setValue(lobbyData);
                        gameJoined = true;
                        apiCallback.joinGameCallback(lobbyData);
                        break;
                    }
                }
                if (lobbyData != null && !lobbyData.getPlayer2().equals("none"))
                    createNewLobby("newLobby", playerName);
                else if (lobbyData != null && lobbyData.getPlayer1().equals("none"))
                    createNewLobby("newLobby", playerName);
            });
        }
    }


    @Override
    public void getOpponentData(String opponentName) {
        gameRef.child(opponentName).get().addOnCompleteListener(task -> {
            System.out.println("Get Opponent Data: ");
            opponentData = task.getResult().getValue(PlayerData.class);
            opponentCallback.setOpponentData(opponentData);
        });
    }

    @Override
    public void resetJoinGameBooleans() {
        this.gameJoined = false;
        lobbyData = new LobbyData();
    }

    @Override
    public void setApiCallback(lobbyDataCallback apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    public void setGameCallback(opponentDataCallback oponentCallback) {
        this.opponentCallback = oponentCallback;
    }

    @Override
    public void deletePlayerData(String playerName) {
        gameRef.child(playerName).removeValue();
    }
}

