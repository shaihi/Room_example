package com.shaihi.room_example;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insertStudent(Students student);

    @Query("SELECT * FROM Students")
    List<Students> getAllStudents();
}
