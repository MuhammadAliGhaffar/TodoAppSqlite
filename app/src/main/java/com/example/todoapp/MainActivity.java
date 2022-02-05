package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.todoapp.Adapter.TaskAdapter;
import com.example.todoapp.Model.Task;
import com.example.todoapp.SqliteDatabase.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private FloatingActionButton floatingButton;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);
        recyclerView = findViewById(R.id.recyclerView);
        floatingButton = findViewById(R.id.floatingButton);

        floatingButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddItem.class);
            startActivity(intent);
        });


        taskAdapter = new TaskAdapter(db.getAllTasks());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        taskAdapter.setListener((task) -> {
            Intent intent = new Intent(MainActivity.this, AddItem.class);
            intent.putExtra("id", task.getId());
            intent.putExtra("taskName", task.getTaskName());
            intent.putExtra("taskDescription", task.getTaskDescription());
            startActivity(intent);
        });
    }
}