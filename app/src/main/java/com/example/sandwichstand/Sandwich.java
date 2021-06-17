package com.example.sandwichstand;

import java.util.UUID;


public class Sandwich {
    public String id;
    //    String customer_name;
    public int pickles;
    public boolean hummus;
    public boolean tahini;
    public String comment;
    public String status;

    public Sandwich(int p, boolean h, boolean t, String comment) {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.pickles = p;
        this.hummus = h;
        this.tahini = t;
        this.comment = comment;
        this.status = "waiting";

    }

    public Sandwich(String id, int p, boolean h, boolean t, String comment, String status) {

        this.id = id;
        this.pickles = p;
        this.hummus = h;
        this.tahini = t;
        this.comment = comment;
        this.status = status;

    }

    public Sandwich() {

    }


    public Sandwich stringToItem(String itemString) {

        try {
            String[] split = itemString.split("#");
            String id = split[0];
            int pickles = Integer.parseInt(split[1]);
            boolean hummus = Boolean.parseBoolean(split[2]);
            boolean tahini = Boolean.parseBoolean(split[3]);
            String comment = split[4];
            String status = split[5];


            return new Sandwich(id, pickles, hummus, tahini, comment, status);

        } catch (Exception e) {
            return null;
        }
    }

    public String getId() {
        return this.id;
    }

    public String itemToString() {
        return this.id + "#" + String.valueOf(this.pickles) + "#" + String.valueOf(this.hummus)
                + "#" + String.valueOf(this.tahini) + "#" + this.comment + "#" + this.status;
    }

    public boolean getHummus() {
        return this.hummus;
    }

    public boolean getTahini() {
        return this.tahini;
    }

    public String getComment() {
        return comment;
    }

    public int getPickles() {
        return pickles;
    }

    public String getStatus() {
        return status;
    }
}
