package com.app.riddle.models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.UUID;

public class AnonimousPostModel {

    public String id;
    public String body;
    public String title;
    public String country;
    public Integer likeCounter;

    public AnonimousPostModel(String body, String title) {
        this.id = new UUID(12,12).toString();
        this.body = body;
        this.title = title;
        this.country = "es";
        this.likeCounter = 0;
    }

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

    public void save(DatabaseReference databaseReference) {
        databaseReference.child(this.id).child("body").setValue(body);
        databaseReference.child(this.id).child("title").setValue(title);
        databaseReference.child(this.id).child("country").setValue(country);
        databaseReference.child(this.id).child("likeCounter").setValue(likeCounter);
    }

}