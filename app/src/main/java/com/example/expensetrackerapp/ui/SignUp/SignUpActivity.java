package com.example.expensetrackerapp.ui.SignUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.expensetrackerapp.R;
import com.example.expensetrackerapp.model.User;
import com.example.expensetrackerapp.ui.Login.LoginActivity;

public class SignUpActivity extends AppCompatActivity {


    EditText Name, Email, Password, ConPassword;
    TextView Login;
    Button signup;

    SignUpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        Login = findViewById(R.id.login);
        signup = findViewById(R.id.btn_signup);
        Name = findViewById(R.id.fullname);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        ConPassword = findViewById(R.id.repPassword);
        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToLoginPage();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String conPassword = ConPassword.getText().toString();

                if (name.isEmpty()) {
                    Name.setError("Name is required");
                    return;
                }
                if (email.isEmpty()) {
                    Email.setError("Email is required");
                    return;
                }
                if (password.isEmpty()) {
                    Password.setError("Password is required");
                    return;
                }
                if (conPassword.isEmpty()) {
                    ConPassword.setError("Repeat password");
                    return;
                } else {
                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(password);
                    viewModel.registerUser(user);
                    sendUserToLoginPage();
                }
            }
        });
    }

    private void sendUserToLoginPage() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}