package com.shaihi.room_example;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Students.class}, version=1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance;

    public abstract StudentDao studentDao();

    public static synchronized AppDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class, "student database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
