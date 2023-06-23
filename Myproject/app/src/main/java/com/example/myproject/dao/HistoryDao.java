package com.example.myproject.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myproject.entity.History;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert
    public void insert(History history);

    @Update
    public void update(History history);

    @Delete
    public void delete(History history);

    @Query("SELECT * FROM histories WHERE user_id = (:user_id)")
    public List<History> getHistories(int user_id);

}
