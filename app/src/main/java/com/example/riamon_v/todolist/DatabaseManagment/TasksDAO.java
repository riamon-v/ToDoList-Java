package com.example.riamon_v.todolist.DatabaseManagment;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface TasksDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(TaskCard task);

    @Update
    void updateTask(TaskCard task);

    @Delete
    void deleteTask(TaskCard task);

    @Query("SELECT * FROM tasks WHERE status = :status")
    List<TaskCard> getTaskByStatus(int status);

    @Query("SELECT * FROM tasks WHERE id = :id")
    TaskCard getTaskById(int id);
}
