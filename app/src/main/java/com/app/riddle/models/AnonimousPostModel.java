package com.app.riddle.models;

import com.google.firebase.database.DataSnapshot;

public class AnonimousPostModel {

    public String id;
    public String body;
    public String title;
    public String country;
    public Integer likeCounter;

    public AnonimousPostModel(DataSnapshot item_snapshot) {
        this.id = item_snapshot.getKey().toString();

        this.body = item_snapshot.child("body").exists()
                ? item_snapshot.child("body").getValue().toString()
                : "";

        this.title = item_snapshot.child("title").exists()
                ? item_snapshot.child("title").getValue().toString()
                : id;

        this.country = item_snapshot.child("country").exists()
                ? item_snapshot.child("country").getValue().toString()
                : "";

        this.likeCounter = item_snapshot.child("likeCounter").exists()
                ? new Integer(item_snapshot.child("likeCounter").getValue().toString())
                : 0;

    }
}