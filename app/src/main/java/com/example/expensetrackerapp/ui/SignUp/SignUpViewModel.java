package com.example.expensetrackerapp.ui.SignUp;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.expensetrackerapp.Callback.CallBack;
import com.example.expensetrackerapp.model.User;
import com.example.expensetrackerapp.repository.User.UserRepository;
import com.example.expensetrackerapp.repository.User.UserRepositoryImpl;

public class SignUpViewModel extends AndroidViewModel implements CallBack {

    private UserRepository userRepository;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepositoryImpl(this, application);
    }

    public void registerUser(User user) {
         userRepository.registerUser(user);
    }

    @Override
    public void getMessage(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_SHORT).show();
    }
}
