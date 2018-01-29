package com.example.riamon_v.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

//android architecture

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


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
/*        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);*/
        startActivity(intent);
    }

    /*public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();

        newFragment.show(MainActivity.this.getFragmentManager(), "dateTask");
       // ((EditText) findViewById(R.id.date)).setText(((DatePickerFragment)newFragment).getDate());
    }*/
}

