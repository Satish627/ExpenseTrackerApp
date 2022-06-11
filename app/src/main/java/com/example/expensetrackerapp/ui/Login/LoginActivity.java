package com.example.expensetrackerapp.ui.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.expensetrackerapp.R;
import com.example.expensetrackerapp.ui.MainActivity;
import com.example.expensetrackerapp.ui.SignUp.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    Button loginBtn;
    TextView signUp;
    EditText Email,Password;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginBtn = findViewById(R.id.btn_login);
        signUp= findViewById(R.id.signup);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        //Initialize firebase auth
        mAuth=FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();

                if (email.isEmpty()) {
                    Email.setError("Email is required!");
                    return;
                }
                if (password.isEmpty()) {
                    Password.setError("Password is required!");
                    return;
                }
                else {
                    System.out.println(email + password);
                    loginViewModel.login(email,password);
                    showMainActivity();
                }}});

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void showMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}