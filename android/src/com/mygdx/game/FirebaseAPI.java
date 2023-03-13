package com.mygdx.game;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseAPI implements API {
    private FirebaseDatabase rtdm;
    private DatabaseReference myRef;

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
                System.out.println("Hallo.");
                String res = task.getResult().getValue().toString();
                messages.add(res);
            }
        });
    }
}
