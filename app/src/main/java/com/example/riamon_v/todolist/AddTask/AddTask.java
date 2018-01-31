package com.example.riamon_v.todolist.AddTask;

import android.app.DialogFragment;
import android.content.Intent;
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
import com.example.riamon_v.todolist.MainActivity;
import com.example.riamon_v.todolist.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AddTask extends AppCompatActivity {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

    private EditText title;
    private EditText content;
    private EditText date;
    private FloatingActionButton validate;
    private Spinner spinner;
    private int idTask;
    private TaskCard task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        date = findViewById(R.id.date);
       /* validate = findViewById(R.id.validateTask);
        validate.setClickable(false);*/
        spinner = findViewById(R.id.spinner);
        idTask = getIntent().getIntExtra("idTask", -1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.list_choice, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        if (idTask == -1)
            task = new TaskCard();
        else
          setTaskEditable();

        /*title.addTextChangedListener(new TextWatcher() {
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
        });*/
    }

    private void setTaskEditable() {
        task = DatabaseHandler.getInstance(this).getTaskDao().getTaskById(idTask);
        title.setText(task.getTitle());
        content.setText(task.getContent());
        date.setText(dateFormat.format(task.getDate()));
        spinner.setSelection(task.getStatus());

        validate.setClickable(true);
        validate.getBackground().setColorFilter(0xFF99CC00, PorterDuff.Mode.SRC_ATOP);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(AddTask.this.getFragmentManager(), "dateTask");
    }

    public void validateTask(View v) {
        Intent intent = new Intent(this, MainActivity.class);

        task.setTitle(title.getText().toString());
        task.setContent(content.getText().toString());
        task.setStatus(spinner.getSelectedItemPosition());
        try {
            task.setDate(dateFormat.parse(date.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (idTask == -1)
            DatabaseHandler.getInstance(this).getTaskDao().insertTask(task);
        else
            DatabaseHandler.getInstance(this).getTaskDao().updateTask(task);
        AddTask.this.finish();
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
