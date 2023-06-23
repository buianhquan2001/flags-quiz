package com.example.myproject.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.example.myproject.dao.FlagDao;
import com.example.myproject.dao.HistoryDao;
import com.example.myproject.entity.History;

@Database(entities = {History.class}, version = 1)
public abstract class HistoryDatabase extends androidx.room.RoomDatabase {
    private static final String dbName = "history_databasenew";
    private static HistoryDatabase instance;

    public abstract HistoryDao historyDao();

    public static synchronized HistoryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), HistoryDatabase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
