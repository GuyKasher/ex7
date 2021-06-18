package com.example.sandwichstand;

import java.util.UUID;


public class Sandwich {
    public String id;
    public String customer_name;
    public int pickles;
    public boolean hummus;
    public boolean tahini;
    public String comment;
    public String status;

    public Sandwich(int p, boolean h, boolean t, String comment, String customer_name) {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.pickles = p;
        this.hummus = h;
        this.tahini = t;
        this.comment = comment;
        this.status = "waiting";
        this.customer_name = customer_name;

    }

    public Sandwich(String id, int p, boolean h, boolean t, String comment, String status, String customer_name) {

        this.id = id;
        this.pickles = p;
        this.hummus = h;
        this.tahini = t;
        this.comment = comment;
        this.status = status;
        this.customer_name = customer_name;

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
            String name = split[6];


            return new Sandwich(id, pickles, hummus, tahini, comment, status, name);

        } catch (Exception e) {
            return null;
        }
    }

    public String getId() {
        return this.id;
    }

    public String itemToString() {
        return this.id + "#" + String.valueOf(this.pickles) + "#" + String.valueOf(this.hummus)
                + "#" + String.valueOf(this.tahini) + "#" + this.comment + "#" + this.status + "#" +
                this.customer_name;
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

    public String getCustomer_name() {
        return customer_name;
    }
}
