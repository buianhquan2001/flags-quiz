package com.example.myproject.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "histories")
public class History {
    @PrimaryKey
    @NonNull private Integer id;

    private int correct_number;

    private int user_id;
    private String time_finished;


    public History(int correct_number, int user_id, String time_finished) {
        this.correct_number = correct_number;
        this.user_id = user_id;
        this.time_finished = time_finished;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCorrect_number() {
        return correct_number;
    }

    public void setCorrect_number(int correct_number) {
        this.correct_number = correct_number;
    }

    public String getTime_finished() {
        return time_finished;
    }

    public void setTime_finished(String time_finished) {
        this.time_finished = time_finished;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
