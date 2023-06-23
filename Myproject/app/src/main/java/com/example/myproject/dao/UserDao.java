package com.example.myproject.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myproject.entity.User;

@Dao
public interface UserDao {
    @Insert
    public void insert(User user);

    @Update
    public void update(User user);

    @Delete
    public void delete(User user);

    @Query("SELECT * FROM users WHERE username = (:username) and password = (:password)")
    public User checkLogin(String username, String password);

}
