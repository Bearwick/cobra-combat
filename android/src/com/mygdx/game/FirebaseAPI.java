package com.mygdx.game;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mygdx.game.data.LobbyData;
import com.mygdx.game.data.PlayerData;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FirebaseAPI implements API {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseDatabase rtdm;
    private DatabaseReference lobbyRef;
    private DatabaseReference gameRef;
    private Boolean gameJoined;
    private Boolean gameCreated;
    private LobbyData lobbyData;
    public lobbyDataCallback apiCallback;
    public oponentDataCallback oponentCallback;
    PlayerData opponentData;
    public FirebaseAPI() {
        this.rtdm = FirebaseDatabase.getInstance("https://cobra-combat-default-rtdb.europe-west1.firebasedatabase.app/");

        this.lobbyRef = rtdm.getReference("Lobbies");
        this.gameRef = rtdm.getReference("PlayerData");
        lobbyData = new LobbyData();
        opponentData = new PlayerData();
        gameCreated=false;
        gameJoined=false;

        //myRef.child("example").setValue("Kan også2 endre på barna!");
    }

    @Override
    public void sendPos(PlayerData data) {
        gameRef.child(data.getNickName()).setValue(data);
    }


    //  ---------- Setters Game Lobby ----------
    @Override
    public void createNewLobby(String lobbyName, String playerName) {
        if(!gameJoined){
            DatabaseReference gameref = lobbyRef.child(lobbyName);
            LobbyData data = new LobbyData();
            data.setPlayer1(playerName);
            gameref.setValue(data);
            gameJoined=true;
            apiCallback.createGameCallback();
        }

    }
    //  ---------- Getters Game Lobby ----------
    @Override
    public void FindLobby(String playerName) {
        joinLobby(playerName);
    }
    @Override
    public void deleteLobby(String lobby) {

    }

    @Override
    public void checkForNewPlayers(String lobbyName) {
        if(!gameJoined){
            lobbyRef.child(lobbyName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    lobbyData = task.getResult().getValue(LobbyData.class);
                    if (!lobbyData.getPlayer2().equals("none")){
                        gameJoined=true;
                        apiCallback.joinGameCallback(lobbyData);

                    }
                }
            });
        }
    }

    @Override
    public void joinLobby(String playerName) {
        if (!gameJoined){
            lobbyRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    for (DataSnapshot ds : task.getResult().getChildren()){
                        lobbyData = ds.getValue(LobbyData.class);

                        if(lobbyData.getPlayer2().equals("none") && !lobbyData.getPlayer1().equals("none")){
                            lobbyData.setPlayer2(playerName);
                            DatabaseReference gameref = lobbyRef.child(ds.getKey());
                            gameref.setValue(lobbyData);
                            gameJoined=true;
                            apiCallback.joinGameCallback(lobbyData);
                            break;
                        }
                    }
                    if(!lobbyData.getPlayer2().equals("none"))
                        createNewLobby("newLobby", playerName);
                    else if (lobbyData.getPlayer1().equals("none"))
                        createNewLobby("newLobby", playerName);
                }
            });
        }
    }

    @Override
    public void getMessage(ArrayList<String> messages) {
        //This never runs,
        gameRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                System.out.println("Android: fetch massaaaage");
                String res = task.getResult().getValue().toString();
                messages.add(res);
                System.out.println(task);
            }
        });

    }

    @Override
    public void getOponentData(String oponentName) {
        gameRef.child(oponentName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                System.out.println("Get Oponent Data: ");
                opponentData = task.getResult().getValue(PlayerData.class);
                oponentCallback.setOponentData(opponentData);

            }
        });
    }
    @Override
    public void resetJoinGameBooleans(){
        this.gameJoined = false;
        this.gameCreated = false;
    }
    @Override
    public void setApiCallback(lobbyDataCallback apiCallback) {
        this.apiCallback = apiCallback;
    }
    @Override
    public void setGameCallback(oponentDataCallback oponentCallback) {
        this.oponentCallback = oponentCallback;
    }
}

