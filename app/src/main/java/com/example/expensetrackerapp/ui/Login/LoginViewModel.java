package com.example.expensetrackerapp.ui.Login;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;

import com.example.expensetrackerapp.Callback.CallBack;
import com.example.expensetrackerapp.repository.User.UserRepository;
import com.example.expensetrackerapp.repository.User.UserRepositoryImpl;

public class LoginViewModel extends AndroidViewModel implements CallBack {
private UserRepository userRepository;

    public LoginViewModel(Application app) {
        super(app);
        userRepository = new UserRepositoryImpl(this,app);
    }
    public void login(String email,String password){
        userRepository.login(email, password);
    }

    @Override
    public void getMessage(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_SHORT).show();
    }
}
