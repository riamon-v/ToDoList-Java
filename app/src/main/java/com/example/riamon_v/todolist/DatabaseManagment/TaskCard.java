package com.example.riamon_v.todolist.DatabaseManagment;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;


@Entity (tableName = "tasks")
public class TaskCard {

        @PrimaryKey (autoGenerate = true)
        public int id;

        public String title;
        public String content;
        //public Date date;
        public int status;

        @Ignore
        int a;
}
