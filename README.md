# Clockworks: Task and Time Management App

Clockworks is a minimalistic productivity app designed to help users manage tasks, set reminders, and track progress, boosting productivity and facilitating better organization.

## Overview

Clockworks provides a streamlined user experience with a clutter-free interface, allowing users to stay focused and get tasks done efficiently. The app includes a Pomodoro timer, a Kanban board, a reminder tool, and a calendar for scheduling and managing daily activities.

## Objectives

- **Minimalistic UI**: Create a task management app with a streamlined user interface that minimizes distractions.
- **Task Management**: Visualize tasks on a Kanban board and manage them with ease.
- **Pomodoro Timer**: Increase productivity with a simple Pomodoro timer.
- **Reminders**: Quickly set and manage reminders for important tasks.
- **Calendar**: Schedule and manage daily activities with a calendar view.

## Features

1. **Kanban Board**: Manage tasks with columns such as "To-do" and "Completed", and move tasks between columns.
2. **Reminders**: Set reminders for specific dates and times, with options for one-time or repeatable reminders.
3. **Pomodoro Timer**: Focus on tasks with a work/break timer to enhance productivity.
4. **Calendar**: Plan out months, weeks, and days, with different calendar categories and color-coding.

## Target Audience

- **Students**: Use the Pomodoro timer for efficient study sessions and the Kanban board for visualizing assignment progress.
- **Remote Workers/Students**: Manage tasks and reminders effectively, even without a physical office or classroom.
- **Teachers**: Use the calendar to track grading deadlines and the Pomodoro timer for classroom activities.
- **General Users**: Anyone needing task management and productivity tools can benefit from Clockworks.

## Screens

1. **Pomodoro Timer Screen**:
   - Work and break screens
   - Start, stop, and reset the timer.
   

2. **Kanban Board Screen**:
   - "In Progress" section shows the current task.
   - "To Do" section for the task queue.
   - "Completed" section for finished tasks.
   - Add and reorder tasks with ease.

3. **Reminder Screen**:
   - Set different times for reminders.
   - Options for daily, one-time, or repeatable reminders.
   - Edit reminder details easily.

4. **Calendar Screen**:
   - Plan out months, weeks, and days.
   - Different calendar categories (e.g., School, Work, Personal).
   - Color-coded events for easy organization.
   - Add, edit, and remove calendar events.

## Data File

**MotivationQuotes.csv**:
- Contains motivational quotes to be randomly loaded and displayed.
- Columns: quote_id, quote_string, quote_author.

## Testing Instructions
- _Pomodoro Timer_: Push "**Start**" to begin the 25 minute work timer. To pause the timer, push "**Pause**" and to resume, push "**Resume**". You can reset back to the beginning of the work timer by pushing "Reset". Once the work timer ends, the 5 minute break timer begins. Simply wait for this timer to end and a button will appear to return to the beginning of the work timer.
- _Kanban Board_: Push the "**+**" button to add a task to the queue. If there are no tasks prior to adding, this will be the "current task" and will show up in the "**In Progress**" area. If more tasks are added, they will be placed in a queue shown in to "**To Do**" area. To make a task from the "To Do" section a current task, simply click on it and the current task prior to clicking will be placed in the front of the queue. Once you have completed the current task, simply click on it and push "**Finish Task**".
- _Reminders_: The beginning screen shows a "**+**" button, which takes you to add a reminder, and a "**My Reminders**" button which takes you to your current reminders. When taken to the add reminders screen, type what you want to be reminded and what time the app will remind you, then either "**Add**" to Today's reminders or "**Add To Week**" to this week's reminders. Click either Add button to take you to your current reminders (Today or This Week depending on the button). To mark a reminder as completed, toggle the the button to the left.
- _Motivation_: Simply open up the screen to be greeted with a famous quote to get you motivated. 

## Future Enhancements

- Customizing themes and layout of the app.
- Syncing with Google and Apple calendars.

## Known Issues
- The Pomodoro Timer always resets when switching to a different screen (i.e., the Knaban Board screen).
- The queue for the Kanban Board isn't saved when adding a reminder.
- Reminders don't save when switching to a different screen.

## Getting Started

### Prerequisites

- Android Studio
- Java Development Kit (JDK)

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/UTSA-CS-3443/Clockworks.git
   ```
2. Open the project in Android Studio.
3. Build and run the project on an emulator or a physical device.


## Team Members

Omar Mian, 
Tibian Elsheikh, 
Roman Garanzuay, 
Raul Martinez



