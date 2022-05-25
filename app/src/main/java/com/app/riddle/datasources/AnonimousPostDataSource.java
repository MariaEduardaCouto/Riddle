package com.app.riddle.datasources;

import androidx.annotation.NonNull;

import com.app.riddle.helpers.Callback;
import com.app.riddle.models.AnonimousPostModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AnonimousPostDataSource {
    public static AnonimousPostDataSource shared = new AnonimousPostDataSource();

    private ArrayList<AnonimousPostModel> postList = new ArrayList<AnonimousPostModel>();

    public void fetch(final Callback callback){

        final DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference().child("AnonimousPost");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<AnonimousPostModel> postList = new ArrayList<AnonimousPostModel>();

                for (DataSnapshot item_snapshot : dataSnapshot.getChildren()) {
                    postList.add(new AnonimousPostModel(item_snapshot));
                }

                AnonimousPostDataSource.this.postList = postList;
                callback.onSuccess(postList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError("Error downloading collection MyCollectionName: "+databaseError.getMessage());
            }
        };


        databaseReference.addValueEventListener(eventListener);
    }
}