package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {

            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();

            if ((username.equals("user") || username.equals("admin"))
                    && password.equals("123456")) {

                Intent intent = new Intent(MainActivity.this, TodoActivity.class);
                startActivity(intent);

            } else {

                Toast.makeText(this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();

            }
        });
    }
}