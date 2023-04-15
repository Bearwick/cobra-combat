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
    private DatabaseReference myRef;
    private Map<String, Boolean> map;
    private Boolean newPlayerHasJoinedLobby = false;
    private Boolean joinedGame = false;

    public FirebaseAPI() {
        this.rtdm = FirebaseDatabase.getInstance("https://cobra-combat-default-rtdb.europe-west1.firebasedatabase.app/");

        this.lobbyRef = rtdm.getReference("Lobbies");
        this.myRef = rtdm.getReference("PlayerData");

        //myRef.child("example").setValue("Kan også2 endre på barna!");
    }

    @Override
    public void sendPos(PlayerData data) {
        myRef.setValue(data);
    }


    //  ---------- Setters Game Lobby ----------
    @Override
    public void createNewLobby(String lobbyName, LobbyData data) {
        DatabaseReference gameref = lobbyRef.child(lobbyName);
        gameref.setValue(data);

    }
    //  ---------- Getters Game Lobby ----------
    @Override
    public void FindLobby() {

    }
    @Override
    public void deleteLobby(String lobby) {

    }

    @Override
    public boolean checkForNewPlayers(String lobbyName) {
        lobbyRef.child(lobbyName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String lobbyState = task.getResult().getValue().toString();
                System.out.println(lobbyState);
                System.out.println(!lobbyState.contains("player2=none"));
                newPlayerHasJoinedLobby = !lobbyState.contains("player2=none}}");
            }
        });

        return newPlayerHasJoinedLobby;
    }

    @Override
    public Boolean joinLobby(String playerName) {
        System.out.println("yoyo4");
        lobbyRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                for (DataSnapshot ds : task.getResult().getChildren()){
                    LobbyData data = ds.getValue(LobbyData.class);
                    if(data.getPlayer2().equals("none")){
                        data.setPlayer2(playerName);
                        DatabaseReference gameref = lobbyRef.child(ds.getKey());
                        gameref.setValue(data);
                        joinedGame=true;
                        break;
                    }

                }
            }
        });
        return joinedGame;
    }
    @Override
    public void getMessage(ArrayList<String> messages) {
        //This never runs,
        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                System.out.println("Android: fetch massaaaage");
                String res = task.getResult().getValue().toString();
                messages.add(res);
                System.out.println(task);
            }
        });
    }

}
