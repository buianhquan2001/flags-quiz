package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproject.dao.UserDao;
import com.example.myproject.database.UserDatabase;
import com.example.myproject.entity.User;

public class DangNhap extends AppCompatActivity {
    private EditText username, password;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_username = username.getText().toString();
                String str_password = password.getText().toString();
                UserDao userDao = UserDatabase.getInstance(getApplicationContext()).userDao();
                User user = userDao.checkLogin(str_username, str_password);
                if (user == null || str_username.isEmpty() || str_password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Thông tin không hợp lệ", Toast.LENGTH_LONG).show();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("saveData", Context.MODE_PRIVATE).edit();
                    editor.putString("username", str_username);
                    editor.putInt("id", user.getId());
                    editor.commit();

                    startActivity(new Intent(getApplicationContext(), Game.class));
                }
            }
        });
    }
}