package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproject.dao.UserDao;
import com.example.myproject.database.UserDatabase;
import com.example.myproject.entity.User;

public class DangKy extends AppCompatActivity {
    private EditText username, password, repassword;
    private Button btnDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        btnDangKy = findViewById(R.id.btnRegister);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_username = username.getText().toString();
                String str_password = password.getText().toString();
                String str_repassword = repassword.getText().toString();

                if (!str_password.equals(str_repassword) || str_username.isEmpty()
                        || str_password.isEmpty() || str_repassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Thông tin không hợp lệ", Toast.LENGTH_LONG).show();
                } else {
                    User user = new User(str_username, str_password);
                    UserDao userDao = UserDatabase.getInstance(getApplicationContext()).userDao();
                    userDao.insert(user);
                    Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


}