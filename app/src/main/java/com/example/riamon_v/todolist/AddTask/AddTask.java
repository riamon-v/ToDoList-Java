package com.example.riamon_v.todolist.AddTask;

import android.app.DialogFragment;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.riamon_v.todolist.DatabaseManagment.DatabaseHandler;
import com.example.riamon_v.todolist.DatabaseManagment.TaskCard;
import com.example.riamon_v.todolist.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddTask extends AppCompatActivity {

    EditText title;
    FloatingActionButton validate;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        title = findViewById(R.id.title);
        validate = findViewById(R.id.validateTask);
        validate.setClickable(false);
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.list_choice, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

              if (title.getText().length() > 0) {
                   validate.setClickable(true);
                   validate.getBackground().setColorFilter(0xFF99CC00, PorterDuff.Mode.SRC_ATOP);
               }
                else {
                   validate.setClickable(false);
                   validate.getBackground().setColorFilter(0xFFD6D7D7, PorterDuff.Mode.SRC_ATOP);
               }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(AddTask.this.getFragmentManager(), "dateTask");
    }

    public void validateTask(View v) {
        TaskCard task = new TaskCard();

        task.setTitle(title.getText().toString());
        task.setContent(((EditText) findViewById(R.id.content)).getText().toString());
        task.setStatus(spinner.getSelectedItemPosition());
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            task.date = format.parse(((EditText) findViewById(R.id.date)).getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DatabaseHandler.getInstance(this).getTaskDao().insertTask(task);
        AddTask.this.finish();
    }
}
