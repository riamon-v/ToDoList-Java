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
    public void insertTask(TaskCard task);

    @Update
    public void updateTask(TaskCard task);

    @Delete
    public void deleteTask(TaskCard task);

    @Query("SELECT * FROM tasks WHERE status = :s")
    public List<TaskCard> getTaskByStatus(int s);
}
