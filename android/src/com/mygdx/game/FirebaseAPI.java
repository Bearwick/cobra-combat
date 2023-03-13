package com.mygdx.game;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseAPI implements API {
    private FirebaseDatabase rtdm;
    private DatabaseReference myRef;

    public FirebaseAPI() {
        this.rtdm = FirebaseDatabase.getInstance("https://cobra-combat-default-rtdb.europe-west1.firebasedatabase.app");
        this.myRef = rtdm.getReference("testing");
    }

    @Override
    public void sendMessage(int i) {
        myRef.setValue("message " + i);
    }

}
