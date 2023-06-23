package com.example.myproject.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myproject.entity.Flag;

import java.util.List;

@Dao
public interface FlagDao {
    @Insert
    public void insert(Flag flag);

    @Update
    public void update(Flag flag);

    @Delete
    public void delete(Flag flag);

    @Query("DELETE FROM flags WHERE countryName = (:countryName)")
    public void deleteByName(String countryName);

    @Query("SELECT * FROM flags")
    public List<Flag> getAll();

    @Query("SELECT * FROM flags ORDER BY RANDOM () LIMIT 10")
    public List<Flag> getTenQuestion();

    @Query(("SELECT * FROM flags WHERE id != (:id) ORDER BY RANDOM() LIMIT 3"))
    public List<Flag> getThreeWrongAnswer(int id);
}
