package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myproject.dao.HistoryDao;
import com.example.myproject.database.HistoryDatabase;
import com.example.myproject.entity.History;

import java.util.Calendar;

public class KetQua extends AppCompatActivity {
    TextView correct, wrong;

    Button btnRestart, btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);

        correct = findViewById(R.id.correct);
        wrong = findViewById(R.id.wrong);
        btnHome = findViewById(R.id.btnHome);
        btnRestart = findViewById(R.id.btnRestart);

        Intent intent = getIntent();
        int correct_number = intent.getIntExtra("correct", 0);

        correct.setText("Số câu đúng: " + correct_number);
        wrong.setText("Số câu sai: " + (10 - correct_number));

        //Lưu lịch sử
        int user_id = getUserId();
        History history = new History(correct_number, user_id, Calendar.getInstance().getTime().toString());
        HistoryDao historyDao = HistoryDatabase.getInstance(this).historyDao();
        historyDao.insert(history);


        //Về home
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KetQua.this, Game.class));
            }
        });

        //Chơi lại
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KetQua.this, Playing.class));
            }
        });

    }

    public int getUserId() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("saveData", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", 1);
    }
}