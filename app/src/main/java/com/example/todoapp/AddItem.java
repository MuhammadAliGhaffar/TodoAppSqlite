package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todoapp.Model.Task;
import com.example.todoapp.SqliteDatabase.DatabaseHandler;

public class AddItem extends AppCompatActivity {

    private EditText edtId, edtTaskName, edtTaskDescription;
    private Button addButton, updateButton, deleteButton;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        db = new DatabaseHandler(this);
        edtId = findViewById(R.id.edtId);
        edtTaskName = findViewById(R.id.edtTaskName);
        edtTaskDescription = findViewById(R.id.edtTaskDescription);
        addButton = findViewById(R.id.addButton);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        addButton.setOnClickListener(view -> {
            if (edtId.getText().toString().isEmpty() || edtTaskName.getText().toString().isEmpty() ||
                    edtTaskDescription.getText().toString().isEmpty()) {
                Toast.makeText(this, "Fields is empty", Toast.LENGTH_SHORT).show();
            } else {
                db.addTask(new Task(Integer.parseInt(edtId.getText().toString()), edtTaskName.getText().toString(), edtTaskDescription.getText().toString()));
                Toast.makeText(this, "Item successfully Added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddItem.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        updateButton.setOnClickListener(view -> {
            if (edtId.getText().toString().isEmpty() || edtTaskName.getText().toString().isEmpty() ||
                    edtTaskDescription.getText().toString().isEmpty()) {
                Toast.makeText(this, "Fields is empty", Toast.LENGTH_SHORT).show();
            } else {
                db.updateTask(new Task(Integer.parseInt(edtId.getText().toString()), edtTaskName.getText().toString(), edtTaskDescription.getText().toString()));
                Toast.makeText(this, "Item successfully Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddItem.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(view -> {
            if (edtId.getText().toString().isEmpty() || edtTaskName.getText().toString().isEmpty() ||
                    edtTaskDescription.getText().toString().isEmpty()) {
                Toast.makeText(this, "Fields is empty", Toast.LENGTH_SHORT).show();
            } else {
                db.deleteTask(new Task(Integer.parseInt(edtId.getText().toString()), edtTaskName.getText().toString(), edtTaskDescription.getText().toString()));
                Toast.makeText(this, "Item successfully Deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddItem.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int id = bundle.getInt("id");
            String taskName = bundle.getString("taskName");
            String taskDescription = bundle.getString("taskDescription");
            edtId.setText(String.valueOf(id));
            edtTaskName.setText(taskName);
            edtTaskDescription.setText(taskDescription);
        }
    }
}