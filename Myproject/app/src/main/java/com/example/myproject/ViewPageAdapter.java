package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myproject.fragment.CauHoi;
import com.example.myproject.fragment.Menu;
import com.example.myproject.fragment.TrangChu;

public class ViewPageAdapter extends FragmentStateAdapter {


    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TrangChu();
            case 1:
                return new CauHoi();
            case 2:
                return new Menu();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}

