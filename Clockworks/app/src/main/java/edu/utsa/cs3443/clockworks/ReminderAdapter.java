package edu.utsa.cs3443.clockworks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReminderAdapter extends BaseAdapter {
    private Context context; // Context for accessing resources
    private ArrayList<Reminder> reminderList; // List of reminders to display

    // Constructor to initialize the adapter with context and reminder list
    public ReminderAdapter(Context context, ArrayList<Reminder> reminderList) {
        this.context = context;
        this.reminderList = reminderList;
    }

    @Override
    public int getCount() {
        return reminderList.size(); // Return the number of reminders
    }

    @Override
    public Object getItem(int position) {
        return reminderList.get(position); // Return the reminder at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position; // Return the position as the ID
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_reminder, parent, false); // Inflate the custom layout
        }

        TextView reminderText = convertView.findViewById(R.id.reminder_text);
        final ImageView circleImage = convertView.findViewById(R.id.circle_image);

        final Reminder reminder = reminderList.get(position);
        reminderText.setText(reminder.getText() + ": " + reminder.getTime()); // Set the reminder text

        // Update the circle image based on the reminder's filled state
        if (reminder.isCircleFilled()) {
            circleImage.setImageResource(R.drawable.black_circle);
        } else {
            circleImage.setImageResource(R.drawable.white_circle_black_border);
        }

        // Toggle the filled state when the circle image is clicked
        circleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminder.toggleCircleFilled(); // Toggle the filled state
                notifyDataSetChanged(); // Notify adapter to refresh the view
            }
        });

        return convertView; // Return the updated view
    }
}
