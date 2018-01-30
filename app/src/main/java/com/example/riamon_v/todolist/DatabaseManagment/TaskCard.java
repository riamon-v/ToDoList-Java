package com.example.riamon_v.todolist.DatabaseManagment;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;


@Entity (tableName = "tasks")
public class TaskCard {

        @PrimaryKey (autoGenerate = true)
        public int id;

        private String title;
        private String content;

        @TypeConverters({DateConverter.class})
        public Date date;

        private int status;

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }
}
