package com.example.expensetrackerapp.repository.User;

import com.example.expensetrackerapp.model.User;

public interface UserRepository  {
    void registerUser(User user);
    void login(String email, String password);
}
