package com.app.riddle.datasources;

import androidx.annotation.NonNull;

import com.app.riddle.helpers.Callback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SessionDataSource {
    public static SessionDataSource shared = new SessionDataSource();

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public void getCurrentUser(final Callback callback) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        callback.onSuccess(currentUser);
    }

    public  Boolean isUserLogedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }


    public void register(String email, String password, final Callback callback) {

        FirebaseAuth mauth = FirebaseAuth.getInstance();
                mauth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    callback.onSuccess(FirebaseAuth.getInstance().getCurrentUser());
                }else {
                    callback.onError("Error sign in");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onError("ERROR WITH FIREBASE");
            }
        });
    }

    public void login(String email, String password, final Callback callback) {
        FirebaseAuth mauth = FirebaseAuth.getInstance();
        mauth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            callback.onSuccess(FirebaseAuth.getInstance().getCurrentUser());
                        }else {
                            callback.onError("Error sign in");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onError("ERROR WITH FIREBASE");
            }
        });
    }

    public void signInAnonymously(final Callback callback) {
        FirebaseAuth.getInstance().signOut();
        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    callback.onSuccess(FirebaseAuth.getInstance().getCurrentUser());
                }else {
                    callback.onError("Error SIGN IN ");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onError("ERROR FIREBASE");
            }
        });

    }

}


