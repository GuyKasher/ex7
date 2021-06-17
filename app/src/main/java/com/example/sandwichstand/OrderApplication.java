package com.example.sandwichstand;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class OrderApplication extends Application {
    private OrderImpl dataBase;
    public OrderImpl getDataBase(){
        return dataBase;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        instance=this;
        this.dataBase=new OrderImpl(this);
    }

    private static OrderApplication instance=null;
    public static OrderApplication getInstance(){
        return instance;
    }



}
