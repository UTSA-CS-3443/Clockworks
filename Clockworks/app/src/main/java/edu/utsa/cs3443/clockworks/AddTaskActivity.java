package edu.utsa.cs3443.clockworks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private KanbanBoard board; // KanbanBoard object to manage tasks
    private String taskText; // String to hold the text of the new task

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display
        setContentView(R.layout.add_task_overlay); // Set the layout for this activity

        board = (KanbanBoard) getIntent().getSerializableExtra("board"); // Retrieve the KanbanBoard object passed from the previous activity
        if (board == null) {
            board = new KanbanBoard(); // Initialize a new KanbanBoard if none was passed
        }

        EditText taskInput = findViewById(R.id.toBeAdded); // Find the EditText for task input
        Button addThis = findViewById(R.id.addThis); // Find the Button to add the task

        addThis.setOnClickListener(new View.OnClickListener() { // Set a click listener for the add button
            @Override
            public void onClick(View view) {
                taskText = taskInput.getText().toString(); // Get the text from the input field
                if (!taskText.isEmpty()) {
                    board.addTask(taskText); // Add the task to the KanbanBoard if the input is not empty
                }

                Intent intent = new Intent(AddTaskActivity.this, KanbanActivity.class); // Create an intent to navigate to KanbanActivity
                intent.putExtra("board", board); // Pass the KanbanBoard object to the next activity
                startActivity(intent); // Start the KanbanActivity
            }
        });
    }
}
