package com.mygdx.game;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebaseAPI implements API {
    private FirebaseDatabase rtdm;
    private DatabaseReference myRef;
    private Map<String, Boolean> map;

    public FirebaseAPI() {
        this.rtdm = FirebaseDatabase.getInstance("https://cobra-combat-default-rtdb.europe-west1.firebasedatabase.app/");
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
    public void createNewLobby() {
        myRef.setValue("NewLobby");
    }
    @Override
    public void updateLobby(String lobby) {
        myRef.setValue("NewLobby");
    }
    @Override
    public void updatePosition(String lobby, String displayName) {myRef.setValue("NewLobby");}
    @Override
    public void incrementScore(String lobby, String displayName) {myRef.setValue("NewLobby");}


    //  ---------- Getters Game Lobby ----------
    @Override
    public void getLobbies(Map<String, Boolean> lobbies) {
        this.myRef = rtdm.getReference("Lobbies");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("Android: Fetch lobbies.");

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    Boolean value = ds.getValue(Boolean.class);
                    lobbies.put(key, value);

                    System.out.println("Lobbies: " + lobbies);
                }
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
