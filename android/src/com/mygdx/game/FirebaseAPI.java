package com.mygdx.game;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FirebaseAPI implements API {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseDatabase rtdm;
    private DatabaseReference ref;
    private DatabaseReference myRef;
    private Map<String, Boolean> map;

    public FirebaseAPI() {
        this.ref = database.getReference("https://cobra-combat-default-rtdb.europe-west1.firebasedatabase.app/");
        this.myRef = rtdm.getReference("testing");
    }

    @Override
    public void sendMessage(int i) {
        myRef.setValue("message " + i);
    }

    @Override
    public void getMessage(ArrayList<String> messages) {
        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                System.out.println("Android: fetch message");
                String res = task.getResult().getValue().toString();
                messages.add(res);
            }
        });
    }

    //  ---------- Setters Game Lobby ----------
    @Override
    public void createNewLobby(String playerName, ArrayList<Boolean> isLoading) {
        DatabaseReference usersRef = ref.child("Lobbies");
        Random r = new Random();
        int n = r.nextInt();
        String Hexadecimal = Integer.toHexString(n);
        String lobby = "Lobby" + Hexadecimal;

        Map<String, Lobby> newLobby = new HashMap<>();
        User player1 = new User(playerName);
        User player2 = new User("Player2");

        Lobby newLobbyInstance = new Lobby(player1, player2);
        newLobby.put(lobby, newLobbyInstance);
        usersRef.setValue(newLobby);
        addLobby(lobby);

        isLoading.set(0, false);
    }
    @Override
    public void updateLobby(String lobby) {
        myRef.setValue("NewLobby");
    }
    @Override
    public void addLobby(String lobby) {
        DatabaseReference usersRef = ref.child("LobbyStatus");
        Map<String, Boolean> lobbyStatus = new HashMap<>();
        lobbyStatus.put(lobby, true);
        usersRef.setValue(lobbyStatus);
    }
    @Override
    public void deleteLobby(String lobby) {
        myRef.setValue("NewLobby");
    }
    @Override
    public void updatePosition(String lobby, String displayName) {myRef.setValue("NewLobby");}
    @Override
    public void incrementScore(String lobby, String displayName) {myRef.setValue("NewLobby");}


    //  ---------- Getters Game Lobby ----------
    @Override
    public void getLobbies(Map<String, Boolean> lobbies, ArrayList<Boolean> isLoading) {
        this.myRef = rtdm.getReference("LobbyStatus");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("Android: Fetching lobbies.");

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    Boolean value = ds.getValue(Boolean.class);
                    lobbies.put(key, value);
                }
                isLoading.set(0, false);
                System.out.println("Lobbies: " + lobbies);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //  Handle errors
                System.out.println("Android: Error while fetching lobbies.");
            }
        });
    }
    @Override
    public void getLobbyStatus(String lobby) {
        myRef.setValue("NewLobby");
    }
    @Override
    public void getOpponentDisplayName(String lobby) {
        myRef.setValue("NewLobby");
    }
    @Override
    public void getOpponentPosition(String lobby) {
        myRef.setValue("NewLobby");
    }





}
