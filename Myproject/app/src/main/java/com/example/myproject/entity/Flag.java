package com.example.myproject.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "flags")
public class Flag {
    @PrimaryKey
    @NonNull private Integer id;
    private String countryName;
    private byte[] image;

    public Flag(String countryName, byte[] image) {
        this.countryName = countryName;
        this.image = image;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
