package edu.utsa.cs3443.clockworks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class FinishTaskActivity extends AppCompatActivity {

    private KanbanBoard board; // KanbanBoard object to manage tasks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display
        setContentView(R.layout.finish_task_overlay); // Set the layout for this activity

        board = (KanbanBoard) getIntent().getSerializableExtra("board"); // Retrieve the KanbanBoard object passed from the previous activity
        if (board == null) {
            board = new KanbanBoard(); // Initialize a new KanbanBoard if none was passed
        }

        TextView currentText = findViewById(R.id.currentTask); // Find the TextView for the current task
        currentText.setText(board.getCurrentTask()); // Set the text to display the current task

        Button finishThis = findViewById(R.id.finishThis); // Find the button to finish the current task

        finishThis.setOnClickListener(new View.OnClickListener() { // Set a click listener for the finish button
            @Override
            public void onClick(View view) {
                board.finishCurrentTask(); // Mark the current task as finished

                Intent intent = new Intent(FinishTaskActivity.this, KanbanActivity.class); // Create intent to navigate to KanbanActivity
                intent.putExtra("board", board); // Pass the updated KanbanBoard object
                startActivity(intent); // Start the KanbanActivity
            }
        });
    }
}
