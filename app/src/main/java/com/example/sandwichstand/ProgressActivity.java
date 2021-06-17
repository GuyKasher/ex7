package com.example.sandwichstand;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProgressActivity extends AppCompatActivity {

    public OrderImpl dataBase = null;
    public FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_order);

        this.db = FirebaseFirestore.getInstance();

        dataBase = OrderApplication.getInstance().getDataBase();

        final DocumentReference docRef = db.collection("orders").document(dataBase.getCurrentSandwich().getId());
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("TAG", "Listen failed.", e);

                    return;
                }

                if (snapshot != null && snapshot.exists()&&snapshot.getData().get("status").equals("ready")) {
                    Log.d("TAG", "Current data: " + snapshot.getData());
                    Intent change = new Intent(ProgressActivity.this, ReadyActivity.class);
                    dataBase.editStatusOrder("ready");
                    startActivity(change);
                    finish();

                } else {
                    Log.d("TAG", "Current data: null");
                }
            }
        });




    }
}
