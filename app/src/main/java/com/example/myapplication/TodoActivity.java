package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TodoActivity extends AppCompatActivity {

    TextView btnAdd;
    LinearLayout todoList;
    LinearLayout upcomingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        btnAdd = findViewById(R.id.btnAdd);
        todoList = findViewById(R.id.todoList);
        upcomingList = findViewById(R.id.upcomingList);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(TodoActivity.this, AddTodoActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    private void addTodayTodo(String name, String note) {

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(name);
        checkBox.setTextSize(17);

        TextView noteText = new TextView(this);
        noteText.setText("Ghi chú: " + note);
        noteText.setTextSize(14);
        noteText.setPadding(45, 0, 10, 10);

        layout.addView(checkBox);
        layout.addView(noteText);

        todoList.addView(layout);
    }

    private void addUpcomingTodo(String name, String note) {

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(this);
        textView.setText(name);
        textView.setTextSize(17);
        textView.setPadding(10, 10, 10, 0);

        TextView noteText = new TextView(this);
        noteText.setText("Ghi chú: " + note);
        noteText.setTextSize(14);
        noteText.setPadding(10, 0, 10, 10);

        layout.addView(textView);
        layout.addView(noteText);

        upcomingList.addView(layout);
    }

    private void addTodo(String name, String time, String note) {

        SimpleDateFormat format =
                new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        format.setLenient(false);

        try {
            Date todoDate = format.parse(time);

            Date today = new Date();
            String todayString = format.format(today);
            Date todayDate = format.parse(todayString);

            if (todoDate.equals(todayDate)) {

                addTodayTodo(name, note);

            } else if (todoDate.after(todayDate)) {

                addUpcomingTodo(name, note);

            } else {

                Toast.makeText(
                        this,
                        "Ngày hoàn thành không được trước hôm nay",
                        Toast.LENGTH_SHORT
                ).show();
            }

        } catch (ParseException e) {

            Toast.makeText(
                    this,
                    "Ngày không hợp lệ",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            String name = data.getStringExtra("name");
            String time = data.getStringExtra("time");
            String note = data.getStringExtra("note");

            if (name != null && !name.isEmpty()
                    && time != null && !time.isEmpty()) {

                if (note == null) {
                    note = "";
                }

                addTodo(name, time, note);
            }
        }
    }
}