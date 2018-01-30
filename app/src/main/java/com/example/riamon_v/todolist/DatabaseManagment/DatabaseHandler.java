package com.example.riamon_v.todolist.DatabaseManagment;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = TaskCard.class, version = 1)
public abstract class DatabaseHandler extends RoomDatabase {

    private static final String DB_NAME = "tasksDatabase.db";
    private static /*volatile*/ DatabaseHandler instance;

    public static DatabaseHandler getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static DatabaseHandler create(final Context context) {
        return Room.databaseBuilder(
                context,
                DatabaseHandler.class,
                DB_NAME).build();
    }

    public static void destroyInstance() {
        instance = null;
    }

    public abstract TasksDAO getTaskDao();
}
