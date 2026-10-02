package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTodoActivity extends AppCompatActivity {

    EditText edtTodoName;
    EditText edtTodoTime;
    EditText edtTodoNote;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        edtTodoName = findViewById(R.id.edtTodoName);
        edtTodoTime = findViewById(R.id.edtTodoTime);
        edtTodoNote = findViewById(R.id.edtTodoNote);
        btnSave = findViewById(R.id.btnSave);

        edtTodoTime.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddTodoActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {

                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(
                                selectedYear,
                                selectedMonth,
                                selectedDay
                        );

                        SimpleDateFormat format =
                                new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

                        edtTodoTime.setText(format.format(selectedDate.getTime()));
                    },
                    year,
                    month,
                    day
            );

            datePickerDialog.show();
        });

        btnSave.setOnClickListener(v -> {

            String name = edtTodoName.getText().toString();
            String time = edtTodoTime.getText().toString();
            String note = edtTodoNote.getText().toString();

            Intent intent = new Intent();

            intent.putExtra("name", name);
            intent.putExtra("time", time);
            intent.putExtra("note", note);

            setResult(RESULT_OK, intent);
            finish();
        });
    }
}