package com.example.riamon_v.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.riamon_v.todolist.AddTask.AddTask;
import com.example.riamon_v.todolist.DatabaseManagment.DatabaseHandler;
import com.example.riamon_v.todolist.DatabaseManagment.TaskCard;
import com.example.riamon_v.todolist.ListManagment.AdapterCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//android architecture

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<TaskCard> tasks;//= new ArrayList<>();

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

    private boolean setRightTask(int id) {
        tasks = DatabaseHandler.getInstance(MainActivity.this).getTaskDao().getTaskByStatus(id);
        recyclerView.setAdapter(new AdapterCard(tasks, new AdapterCard.OnItemClickListener() {
            @Override
            public void onItemClick(TaskCard item) {
                editTask(item.getId());
                //    Log.d("ID ITEM", ": " + item.getId());
            }
        }));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        recyclerView = findViewById(R.id.recyclerView);

        //définit l'agencement des cellules, ici de façon verticale, comme une ListView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //pour adapter en grille comme une RecyclerView, avec 2 cellules par ligne
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        //puis créer un MyAdapter, lui fournir notre liste de villes.
        //cet adapter servira à remplir notre recyclerview
        setRightTask(0);

        final FloatingActionButton button = findViewById(R.id.addTask);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addTask(v);
                /* AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                LayoutInflater inflater = MainActivity.this.getLayoutInflater();

                 builder.setTitle(R.string.title_popup)
                         .setView(inflater.inflate(R.layout.add_task, null))
                         .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int id) {
                                 // sign in the user ...
                             }
                         })
                         .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 //LoginDialogFragment.this.getDialog().cancel();
                             }
                         });
                AlertDialog dialog = builder.create();
                dialog.show();
                // Code here executes on main thread after user presses button*/
            }
        });
    }

    public void addTask(View view) {
        Intent intent = new Intent(this, AddTask.class);
        intent.putExtra("edit", -1);
       // intent.putExtra("Intent", this.getIntent());
/*        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);*/
        startActivity(intent);
    }

    public void editTask(int idTask) {
        Intent intent = new Intent(this, AddTask.class);
        intent.putExtra("idTask", idTask);

        startActivity(intent);
    }
    /*public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();

        newFragment.show(MainActivity.this.getFragmentManager(), "dateTask");
       // ((EditText) findViewById(R.id.date)).setText(((DatePickerFragment)newFragment).getDate());
    }*/
}

