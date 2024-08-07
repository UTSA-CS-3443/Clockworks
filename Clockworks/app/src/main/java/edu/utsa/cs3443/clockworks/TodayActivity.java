package edu.utsa.cs3443.clockworks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class TodayActivity extends Activity {

    private ImageButton buttonArrow;  // Button to navigate back to MyRemindersActivity
    private ListView reminderListView;  // ListView to display reminders
    private ReminderAdapter reminderAdapter;  // Adapter to manage the list of reminders
    private ArrayList<Reminder> reminderList;  // List to hold reminder objects
    private KanbanBoard board;  // Kanban board object for sharing state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        // Retrieve the Kanban board from the intent
        board = (KanbanBoard) getIntent().getSerializableExtra("board");

        // Initialize UI components
        buttonArrow = findViewById(R.id.button_arrow);
        reminderListView = findViewById(R.id.reminder_list);

        // Initialize the list of reminders and add some example reminders
        reminderList = new ArrayList<>();
        reminderList.add(new Reminder("Meeting", "6pm"));
        reminderList.add(new Reminder("Run", "10pm"));

        // Load additional reminders from SharedPreferences
        reminderList.addAll(ReminderUtils.loadReminders(this));

        // Set up the adapter for the ListView
        reminderAdapter = new ReminderAdapter(this, reminderList);
        reminderListView.setAdapter(reminderAdapter);

        // Set up a click listener for the arrow button to navigate back
        buttonArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodayActivity.this, MyRemindersActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
                finish();  // Close the current activity
            }
        });

        // Handle any new reminders passed via the intent
        handleNewReminder();
    }

    private void handleNewReminder() {
        // Retrieve data from the intent
        Intent intent = getIntent();
        String reminderText = intent.getStringExtra("reminder_text");
        String reminderTime = intent.getStringExtra("reminder_time");
        boolean addToWeek = intent.getBooleanExtra("add_to_week", false);

        // If reminder text and time are provided, handle the new reminder
        if (reminderText != null && reminderTime != null) {
            if (addToWeek) {
                // Save the reminder to week reminders (handled in ActivityThisWeek)
                ReminderUtils.saveReminder(this, new Reminder(reminderText, reminderTime));
            } else {
                // Add the new reminder to the top of the list and update the adapter
                reminderList.add(0, new Reminder(reminderText, reminderTime));
                reminderAdapter.notifyDataSetChanged();
            }
        }
    }
}





