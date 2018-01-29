package com.example.riamon_v.todolist;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by riamon_v on 29/01/2018.
 */

@Entity (tableName = "users")
public class TaskCard {
        @PrimaryKey
        public int id;

        public String title;
        public String content;
        public int section;
}
