package edu.utsa.cs3443.clockworks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewReminderActivity extends Activity {

    private EditText reminderEditText; // Input field for reminder text
    private EditText timeEditText; // Input field for reminder time
    private Button addButton; // Button to add reminder for today
    private Button addToWeekButton; // Button to add reminder for this week
    private KanbanBoard board; // KanbanBoard object to manage tasks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder); // Set the content view to the layout file

        board = (KanbanBoard) getIntent().getSerializableExtra("board"); // Get the KanbanBoard object from the Intent

        reminderEditText = findViewById(R.id.edittext_reminder); // Find the reminder input field by its ID
        timeEditText = findViewById(R.id.edittext_when); // Find the time input field by its ID
        addButton = findViewById(R.id.button_add); // Find the Add button by its ID
        addToWeekButton = findViewById(R.id.button_add_to_week); // Find the Add to Week button by its ID

        // Set up an OnClickListener for the Add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddReminder(false);  // Handle adding the reminder for today
            }
        });

        // Set up an OnClickListener for the Add to Week button
        addToWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddReminder(true);   // Handle adding the reminder for this week
            }
        });
    }

    // Method to handle adding a reminder
    private void handleAddReminder(boolean addToWeek) {
        String reminderText = reminderEditText.getText().toString(); // Get the reminder text from input
        String reminderTime = timeEditText.getText().toString(); // Get the reminder time from input

        // Check if either input field is empty
        if (reminderText.isEmpty() || reminderTime.isEmpty()) {
            Toast.makeText(NewReminderActivity.this, "Please fill in both fields", Toast.LENGTH_SHORT).show(); // Show an error message
            return;
        }

        // Create an Intent to return the reminder details to the calling activity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("reminder_text", reminderText); // Pass the reminder text
        resultIntent.putExtra("reminder_time", reminderTime); // Pass the reminder time
        resultIntent.putExtra("add_to_week", addToWeek); // Pass whether to add to this week
        resultIntent.putExtra("board", board); // Pass the KanbanBoard object
        setResult(Activity.RESULT_OK, resultIntent); // Set the result code and data
        finish(); // Finish the activity and return to the previous screen
    }
}





