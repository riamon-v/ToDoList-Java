package com.example.riamon_v.todolist.DatabaseManagment;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

/**
 * Task object in database
 */
@Entity (tableName = "tasks")
public class TaskCard {

        @PrimaryKey (autoGenerate = true)
        private int id;

        private String title;
        private String content;

        @TypeConverters({DateConverter.class})
        private Date date;

        private String time;
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

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public Date getDate() {
                return date;
        }

        public void setDate(Date date) {
                this.date = date;
        }

        public String getTime() {
                return time;
        }

        public void setTime(String time) {
                this.time = time;
        }
}
