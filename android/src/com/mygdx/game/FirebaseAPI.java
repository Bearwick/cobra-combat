package com.mygdx.game;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mygdx.game.sprites.PlayerData;

import java.util.ArrayList;

public class FirebaseAPI implements API {
    private FirebaseDatabase rtdm;
    private DatabaseReference myRef;

    public FirebaseAPI() {
        this.rtdm = FirebaseDatabase.getInstance("https://cobra-combat-default-rtdb.europe-west1.firebasedatabase.app/");

        // In-code example that we can change the database reference to
        // something else on the same level in the rtdm (don't mind the norwenglish)
        this.myRef = rtdm.getReference("a test oioi");
        myRef.child("example").setValue("Kan også endre på barna!");
        // back to the regularly scheduled programming

        this.myRef = rtdm.getReference("testing as of issue number 13 this is only player data for one player");
    }

    @Override
    public void sendPos(PlayerData data) {
        myRef.setValue(data);
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
