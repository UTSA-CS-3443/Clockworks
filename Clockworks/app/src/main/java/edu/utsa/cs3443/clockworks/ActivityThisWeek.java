package edu.utsa.cs3443.clockworks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import edu.utsa.cs3443.clockworks.Reminder;
import edu.utsa.cs3443.clockworks.ReminderAdapter;

public class ActivityThisWeek extends Activity {

    private ImageButton buttonBack; // Button to navigate back to the previous screen
    private ListView reminderListView; // ListView to display reminders
    private ReminderAdapter reminderAdapter; // Adapter to manage the data for the ListView
    private ArrayList<Reminder> reminderList; // List to store reminders
    private KanbanBoard board; // KanbanBoard object passed from the previous activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_week); // Set the layout for this activity

        board = (KanbanBoard) getIntent().getSerializableExtra("board"); // Retrieve the KanbanBoard object passed from the previous activity

        buttonBack = findViewById(R.id.button_arrow); // Initialize the back button
        reminderListView = findViewById(R.id.reminder_list); // Initialize the ListView for reminders

        reminderList = new ArrayList<>(); // Initialize the list to hold reminders
        // Add predefined reminders to the list
        reminderList.add(new Reminder("Grocery Shopping", "Thursday, 1pm"));
        reminderList.add(new Reminder("Doc appt", "Friday, 2:30pm"));
        reminderList.add(new Reminder("Brunch with Jo", "Saturday, 12pm"));

        reminderAdapter = new ReminderAdapter(this, reminderList); // Initialize the adapter with the list of reminders
        reminderListView.setAdapter(reminderAdapter); // Set the adapter for the ListView

        buttonBack.setOnClickListener(new View.OnClickListener() { // Set a click listener for the back button
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityThisWeek.this, MyRemindersActivity.class); // Create an intent to navigate to MyRemindersActivity
                intent.putExtra("board", board); // Pass the KanbanBoard object to the next activity
                startActivity(intent); // Start the next activity
                finish(); // Finish the current activity
            }
        });
    }
}
