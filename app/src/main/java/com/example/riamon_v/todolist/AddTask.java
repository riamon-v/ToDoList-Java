package com.example.riamon_v.todolist;

import android.app.DialogFragment;
import android.graphics.Color;
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

public class AddTask extends AppCompatActivity {

    EditText title;
    FloatingActionButton validate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        title = findViewById(R.id.title);

        validate = findViewById(R.id.validateTask);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.list_choice, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

               if (title.getText().length() > 0) {
                   validate.setEnabled(true);
                   validate.getBackground().setColorFilter(0xFF99CC00, PorterDuff.Mode.SRC_ATOP);
               }
                else {
                   validate.setEnabled(false);
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
}
