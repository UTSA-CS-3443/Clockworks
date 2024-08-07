package edu.utsa.cs3443.clockworks;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ReminderUtils {

    private static final String PREFS_NAME = "reminders_prefs"; // SharedPreferences file name
    private static final String REMINDERS_KEY = "reminders"; // Key for storing reminders

    // Save a reminder to SharedPreferences
    public static void saveReminder(Context context, Reminder reminder) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        ArrayList<Reminder> reminders = loadReminders(context); // Load existing reminders
        reminders.add(0, reminder); // Add new reminder to the top

        Gson gson = new Gson();
        String json = gson.toJson(reminders); // Convert list of reminders to JSON
        editor.putString(REMINDERS_KEY, json); // Save JSON to SharedPreferences
        editor.apply(); // Apply changes asynchronously
    }

    // Load reminders from SharedPreferences
    public static ArrayList<Reminder> loadReminders(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(REMINDERS_KEY, null); // Retrieve JSON string from SharedPreferences

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Reminder>>() {}.getType(); // Define the type of the list
        ArrayList<Reminder> reminders = gson.fromJson(json, type); // Convert JSON to list of reminders

        return (reminders == null) ? new ArrayList<Reminder>() : reminders; // Return empty list if null
    }

    // Clear all reminders from SharedPreferences
    public static void clearReminders(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear(); // Clear all data
        editor.apply(); // Apply changes asynchronously
    }
}
