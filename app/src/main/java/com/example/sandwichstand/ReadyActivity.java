package com.example.sandwichstand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class ReadyActivity extends AppCompatActivity {

    public OrderImpl dataBase = null;
    public FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ready_order);

        Button gotIt= findViewById(R.id.gotIt);


        this.db = FirebaseFirestore.getInstance();
        dataBase = OrderApplication.getInstance().getDataBase();

        gotIt.setOnClickListener(v -> {
//            dataBase.deleteOrder();
            dataBase.editStatusOrder("done");
            Intent intentToOpenEdit = new Intent(v.getContext(), MainActivity.class);
            finish();
            v.getContext().startActivity(intentToOpenEdit);

        });


    }
}
