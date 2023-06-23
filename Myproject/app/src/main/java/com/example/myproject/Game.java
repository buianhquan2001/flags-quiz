package com.example.myproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.dao.FlagDao;
import com.example.myproject.database.FlagDatabase;
import com.example.myproject.entity.Flag;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;

public class Game extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPage;

    private Button button2;


    String[] titles = new String[]{"Trang chủ", "Câu hỏi", "Lịch sử"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        button2 = findViewById(R.id.button2);
        setUsername();
        tabLayout = findViewById(R.id.tabLayout);
        viewPage = findViewById(R.id.viewPage);

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this);

        viewPage.setAdapter(viewPageAdapter);

        new TabLayoutMediator(tabLayout, viewPage, ((tab, position) -> tab.setText(titles[position]))).attach();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeLogin();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    public void setUsername() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveData", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);
        button2.setText("Xin chào " + username + " : Đăng xuất");
    }

    public void removeLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveData", MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

}