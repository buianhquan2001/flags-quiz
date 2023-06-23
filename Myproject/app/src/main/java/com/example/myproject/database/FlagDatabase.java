package com.example.myproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.example.myproject.dao.FlagDao;
import com.example.myproject.dao.UserDao;
import com.example.myproject.entity.Flag;

@Database(entities = {Flag.class}, version = 1)
public abstract class FlagDatabase extends androidx.room.RoomDatabase{
    private static final String dbName = "flag_databasenew";
    private static FlagDatabase instance;

    public abstract FlagDao flagDao();

    public static synchronized FlagDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), FlagDatabase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
