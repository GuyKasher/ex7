package com.example.sandwichstand;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.Set;

public class OrderImpl implements Serializable {


    private static Context context = null;
    private Sandwich curSandwich;
    public FirebaseFirestore db;
    private static SharedPreferences sharedPreferences = null;

    public OrderImpl(Context context) {
        OrderImpl.context = context;
        sharedPreferences = context.getSharedPreferences("local_db_order", Context.MODE_PRIVATE);
        this.db = FirebaseFirestore.getInstance();


        initialize();

    }

    public void initialize() {
        Set<String> keys = sharedPreferences.getAll().keySet();


        for (String k : keys) {
            String itemString = sharedPreferences.getString(k, null);
            Sandwich sandwich = new Sandwich().stringToItem(itemString);
            if (sandwich != null) {
                this.curSandwich = sandwich;
            }
        }
    }


    public Sandwich getCurrentSandwich() {
        return this.curSandwich;
    }


    public void editOrder(int pickles, boolean tahini, boolean hummus, String comment) {
        Sandwich newSandwich = new Sandwich(this.curSandwich.getId(),pickles, tahini, hummus, comment,this.curSandwich.getStatus(),this.getCustomerName());
        this.curSandwich = newSandwich;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(String.valueOf(curSandwich.getId()), curSandwich.itemToString());
        editor.apply();

        db.collection("orders").document(curSandwich.getId()).set(curSandwich);

    }

    public void addNewOrder(int pickles, boolean tahini, boolean hummus, String comment, String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (this.curSandwich!=null){
            editor.remove(String.valueOf(this.curSandwich.getId()));
        }

        Sandwich newSandwich = new Sandwich(pickles, hummus,tahini, comment,name);
        this.curSandwich = newSandwich;

        editor.putString(String.valueOf(newSandwich.getId()), newSandwich.itemToString());
        editor.apply();

        db.collection("orders").document(curSandwich.getId()).set(curSandwich);
    }

    public void deleteOrder() {


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(String.valueOf(this.curSandwich.getId()));
        editor.apply();

        db.collection("orders").document(this.curSandwich.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error deleting document", e);
                    }
                });

    }


    public boolean getHummusStatus(){
        return this.curSandwich.getHummus();

    }

    public boolean getTahiniStatus(){
        return this.curSandwich.getTahini();

    }

    public String getCustomerName(){
        return this.curSandwich.getCustomer_name();
    }
    public String getStatusOrder(){
        return this.curSandwich.getStatus();
    }
    public String getComment(){
        return this.curSandwich.getComment();
    }
    public int getPickles(){
        return this.curSandwich.getPickles();
    }

    public void editStatusOrder(String newStatus) {
        Sandwich newSandwich = new Sandwich(this.curSandwich.getId(),this.getPickles(), this.getTahiniStatus(), this.getHummusStatus(), this.getComment(),newStatus,this.getCustomerName());
        this.curSandwich = newSandwich;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(String.valueOf(curSandwich.getId()), curSandwich.itemToString());
        editor.apply();


    }
}