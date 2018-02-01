package com.example.riamon_v.todolist;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.riamon_v.todolist.DatabaseManagment.DatabaseHandler;
import com.example.riamon_v.todolist.DatabaseManagment.TaskCard;
import com.example.riamon_v.todolist.DatabaseManagment.TasksDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class DataBaseTest {
    private TasksDAO mTaskDao;
    private DatabaseHandler mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, DatabaseHandler.class).build();
        mTaskDao = mDb.getTaskDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void insertTask() throws Exception {
        TaskCard task = new TaskCard();

        task.setTitle("Titre");
        task.setStatus(1);
        mTaskDao.insertTask(task);
        List<TaskCard> tasks = mTaskDao.getTaskByStatus(1);
        assertEquals(tasks.get(0).getTitle(), task.getTitle());
    }

    @Test
    public void updateTask() throws Exception {
        TaskCard task = new TaskCard();
        TaskCard tmp;

        task.setTitle("Titre");
        task.setStatus(1);
        mTaskDao.insertTask(task);
        tmp = task;
        task.setContent("test");
        mTaskDao.updateTask(task);
        List<TaskCard> tasks = mTaskDao.getTaskByStatus(1);
        assertNotEquals(tasks.get(0).getContent(), tmp.getContent());
    }

    @Test
    public void deleteTask() throws Exception {
        TaskCard task = new TaskCard();

        task.setTitle("Titre");
        task.setStatus(1);
        mTaskDao.insertTask(task);
        List<TaskCard> tasks = mTaskDao.getTaskByStatus(1);
        assertEquals(tasks.size(), 1);
        mTaskDao.deleteTask(tasks.get(0));
        assertEquals(mTaskDao.getTaskByStatus(1).size(), 0);
    }

    @Test
    public void getByStatus() {
        TaskCard task = new TaskCard();

        task.setTitle("Titre");
        task.setStatus(1);
        mTaskDao.insertTask(task);
        mTaskDao.insertTask(task);
        task.setStatus(2);
        mTaskDao.insertTask(task);
        List<TaskCard> tasks = mTaskDao.getTaskByStatus(1);
        assertEquals(tasks.size(), 2);
        tasks = mTaskDao.getTaskByStatus(2);
        assertEquals(tasks.size(), 1);
    }
}
