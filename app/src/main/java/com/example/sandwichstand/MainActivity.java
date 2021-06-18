package com.example.sandwichstand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.TtsSpan;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    boolean tahiniStatus;
    boolean hummusStatus;
    public OrderImpl dataBase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null || !savedInstanceState.containsKey("dataBase")) {
            dataBase = OrderApplication.getInstance().getDataBase();
        } else {
            dataBase = (OrderImpl) savedInstanceState.getSerializable("dataBase");
        }
        if (dataBase.getCurrentSandwich()!=null) {
            if (dataBase.getStatusOrder().equals("waiting")) {
                Intent intentToOpenEdit = new Intent(MainActivity.this, EditOrderActivity.class);
                finish();
                startActivity(intentToOpenEdit);
            }
            if (dataBase.getStatusOrder().equals("in-progress")) {
                Intent intentToOpenEdit = new Intent(MainActivity.this, ProgressActivity.class);
                finish();
                startActivity(intentToOpenEdit);
            }
            if (dataBase.getStatusOrder().equals("ready")) {
                Intent intentToOpenEdit = new Intent(MainActivity.this, ReadyActivity.class);
                finish();
                startActivity(intentToOpenEdit);
            }

        }



        setContentView(R.layout.new_order);

        if (savedInstanceState == null || !savedInstanceState.containsKey("dataBase")) {
            dataBase = OrderApplication.getInstance().getDataBase();
        } else {
            dataBase = (OrderImpl) savedInstanceState.getSerializable("dataBase");
        }



        NumberPicker pickleNumber = findViewById(R.id.picklesNumber);
        Switch tahini =  findViewById(R.id.tahiniSwitch);
        Switch hummus =  findViewById(R.id.hummusSwitch);
        Button save= findViewById(R.id.SaveButton);
        EditText comment = findViewById(R.id.comment);
        EditText name = findViewById(R.id.customerName);
        save.setEnabled(false);



        pickleNumber.setMinValue(0);
        pickleNumber.setMaxValue(10);
//        pickleNumber.setValue(0);

        tahiniStatus = false;
        tahini.setChecked(false);

        hummusStatus = false;
        hummus.setChecked(false);

       name.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void afterTextChanged(Editable s) {
                // text did change
                String newText = name.getText().toString();
                save.setEnabled(!newText.equals(""));

            }
        });



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

        save.setOnClickListener(v -> {
            String userInputString = comment.getText().toString();
            String customerName=name.getText().toString();
            int picklesNum=pickleNumber.getValue();
            dataBase.addNewOrder(picklesNum,tahiniStatus,hummusStatus,userInputString,customerName);
            Intent intentToOpenEdit = new Intent(v.getContext(), EditOrderActivity.class);
            finish();
            v.getContext().startActivity(intentToOpenEdit);

        });



        //        pickleNumber.setOnValueChangedListener((NumberPicker.OnValueChangeListener) this);


    }
}