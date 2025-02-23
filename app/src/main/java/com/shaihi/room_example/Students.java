package com.shaihi.room_example;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Students")
public class Students {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public int grade;

    public Students(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
}
