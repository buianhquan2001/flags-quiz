package com.example.myproject.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.example.myproject.dao.UserDao;
import com.example.myproject.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract  class UserDatabase extends androidx.room.RoomDatabase {
    private static final String dbName = "user_database";
    private static UserDatabase instance;

    public abstract UserDao userDao();

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
