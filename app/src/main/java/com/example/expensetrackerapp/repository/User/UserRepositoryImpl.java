package com.example.expensetrackerapp.repository.User;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.expensetrackerapp.Callback.CallBack;
import com.example.expensetrackerapp.model.User;
import com.example.expensetrackerapp.repository.FirebaseConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class UserRepositoryImpl extends LiveData<FirebaseUser> implements UserRepository {
    private FirebaseAuth mAuth;
    private MutableLiveData<FirebaseUser> firebaseUser;
    private final Application app;
    private CallBack callBack;



    public UserRepositoryImpl(CallBack callback, Application app) {
        mAuth = FirebaseConfig.getFirebaseAuth();
        this.app = app;
        firebaseUser = new MutableLiveData<>();
        this.callBack = callback;
    }

    @Override
    public void registerUser(User user) {
        String email = user.getEmail();
        String password = user.getPassword();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if ( task.isSuccessful() ) {
                try {
                    saveUser(user);
                    callBack.getMessage("Successfully signed up!");

                }catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                callBack.getMessage("Signed up failed");
            }
        });
    }

    private void saveUser(User user) {
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uID = mUser.getUid();
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("email", user.getEmail());
        userMap.put("name", user.getName());
        userMap.put("password", user.getPassword());

        DatabaseReference userRef = FirebaseConfig.getDatabaseReference().child("Users").child(uID);
        userRef.setValue(userMap);
    }

    @Override
    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        firebaseUser.postValue(FirebaseConfig.getFirebaseAuth().getCurrentUser());
                        callBack.getMessage("Successfully logged in");
                    } else {
                        firebaseUser.postValue(null);
                        callBack.getMessage(task.getException().getMessage());
                    }
                });
        }
}
