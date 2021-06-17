package com.example.sandwichstand;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class EditOrderActivity extends AppCompatActivity {
    boolean tahiniStatus;
    boolean hummusStatus;
    public OrderImpl dataBase = null;
    public FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_order);

        NumberPicker pickleNumber = findViewById(R.id.picklesNumber);
        Switch tahini =  findViewById(R.id.tahiniSwitch);
        Switch hummus =  findViewById(R.id.hummusSwitch);
        Button save= findViewById(R.id.saveButton);
        Button delete= findViewById(R.id.deleteButton);
        EditText comment = findViewById(R.id.comment);
        this.db = FirebaseFirestore.getInstance();

        dataBase = OrderApplication.getInstance().getDataBase();
        pickleNumber.setMinValue(0);
        pickleNumber.setMaxValue(10);

        pickleNumber.setValue(dataBase.getPickles());

        comment.setText(dataBase.getComment());

        tahiniStatus=dataBase.getTahiniStatus();
        tahini.setChecked(tahiniStatus);
        hummusStatus=dataBase.getHummusStatus();
        hummus.setChecked(hummusStatus);

        //////attach a listener to check for changes in state
        tahini.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //edit here
                tahiniStatus = isChecked; //edit here

            }
        });
        hummus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //edit here
                hummusStatus = isChecked; //edit here

            }
        });

        final DocumentReference docRef = db.collection("orders").document(dataBase.getCurrentSandwich().getId());
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("TAG", "Listen failed.", e);

                    return;
                }

                if (snapshot != null && snapshot.exists()&&snapshot.getData().get("status").equals("in-progress")) {
                    Log.d("TAG", "Current data: " + snapshot.getData());
                    Intent change = new Intent(EditOrderActivity.this, ProgressActivity.class);
//                    dataBase.editStatusOrder("in-progress");
                    startActivity(change);
                    finish();

                } else {
                    Log.d("TAG", "Current data: null");
                }
            }
        });

        save.setOnClickListener(v -> {
            String userInputString = comment.getText().toString();
            int picklesNum=pickleNumber.getValue();
            dataBase.editOrder(picklesNum,tahiniStatus,hummusStatus,userInputString);
        });

        delete.setOnClickListener(v->{
            dataBase.deleteOrder();
            Intent intentToOpenEdit = new Intent(v.getContext(), MainActivity.class);
            finish();
            v.getContext().startActivity(intentToOpenEdit);
        });


    }

}
