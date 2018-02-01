package com.example.riamon_v.todolist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;

import com.example.riamon_v.todolist.AddTask.AddTask;
import com.example.riamon_v.todolist.DatabaseManagment.DatabaseHandler;
import com.example.riamon_v.todolist.DatabaseManagment.TaskCard;
import com.example.riamon_v.todolist.ListManagment.AdapterCard;
import com.example.riamon_v.todolist.ListManagment.CardHolder;
import com.example.riamon_v.todolist.ListManagment.RecyclerItemTouchHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private RecyclerView recyclerView;
    private AdapterCard adapter;
    private List<TaskCard> tasks;
    private ConstraintLayout container;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return setRightTask(0);
                case R.id.navigation_dashboard:
                    return setRightTask(1);
                case R.id.navigation_notifications:
                    return setRightTask(2);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        recyclerView = findViewById(R.id.recyclerView);
        container = findViewById(R.id.container);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        setRightTask(0);
        adapter = new AdapterCard(tasks, new AdapterCard.OnItemClickListener() {
            @Override
            public void onItemClick(TaskCard item) {
                editTask(item.getId());
            }
        });
        recyclerView.setAdapter(adapter);

        final FloatingActionButton button = findViewById(R.id.addTask);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editTask(-1);
                MainActivity.this.finish();
            }
        });
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CardHolder) {
            String name = tasks.get(viewHolder.getAdapterPosition()).getTitle();
            final TaskCard deletedItem = tasks.get(viewHolder.getAdapterPosition());
            final int index = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            DatabaseHandler.getInstance(MainActivity.this).getTaskDao().deleteTask(deletedItem);
            adapter.removeItem(index);

            Snackbar snackbar = Snackbar
                    .make(container, name + " removed from cart!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    DatabaseHandler.getInstance(MainActivity.this).getTaskDao().insertTask(deletedItem);
                    adapter.restoreItem(deletedItem, index);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    private boolean setRightTask(int id) {
        tasks = DatabaseHandler.getInstance(MainActivity.this).getTaskDao().getTaskByStatus(id);

        adapter = new AdapterCard(tasks, new AdapterCard.OnItemClickListener() {
            @Override
            public void onItemClick(TaskCard item) {
                editTask(item.getId());
            }
        });
        recyclerView.setAdapter(adapter);
        return true;
    }

    public void editTask(int idTask) {
        Intent intent = new Intent(this, AddTask.class);
        intent.putExtra("idTask", idTask);

        startActivity(intent);
    }

/*
    public void reload() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }*/
}

