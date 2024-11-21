/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.st10437458_poe_part_three;

import javax.swing.JOptionPane;

/**
 *
 * @author kabel
 */
public class ST10437458_POE_PART_THREE {
    final String[] developers = new String[100];
    final String[] taskNames = new String[100];
    private final String[] taskIDs = new String[100];
    private final int[] taskDurations;
    private final String[] taskStatuses = new String[100];
    int taskCount = 0; // Keeps track of tasks

    private String registeredUsername;
    private String registeredPassword;
    private String firstName;
    private String lastName;

    public ST10437458_POE_PART_THREE() {
        this.taskDurations = new int[100];
    }
    
    public void populateTestData() {
        addTask("Mike Smith", "Create Login", "Create a login system", 5, "To Do");
        addTask("Edward Harrison", "Create Add Features", "Develop additional features", 8, "Doing");
        addTask("Samantha Paulson", "Create Report", "Generate a project report", 2, "Done");
        addTask("Glenda Oberholzer", "Add Arrays", "Implement array handling", 11, "To Do");
    }

    public static void main(String[] args) {
        ST10437458_POE_PART_THREE taskManager = new ST10437458_POE_PART_THREE();
    taskManager.populateTestData(); // Load test data
    taskManager.runApplication();  // Start the application
    }
    
    public void runApplication() {
        boolean loggedIn = false;

        while (true) {
            String menu = """
                    1. Register User
                    2. Login
                    3. Add Tasks (Login Required)
                    4. Show Reports (Login Required)
                    5. Display Done Tasks (Login Required)
                    6. Show Longest Task Duration (Login Required)
                    7. Search Task by Name (Login Required)
                    8. Search Tasks by Developer (Login Required)
                    9. Delete Task by Name (Login Required)
                    10. Exit
                    """;

            String choice = JOptionPane.showInputDialog(menu);
            if (choice == null) break; // Exit if user cancels

            switch (choice) {
                case "1" -> registerUser();
                case "2" -> loggedIn = loginUser();
                case "3" -> {
                    if (loggedIn) addTasks();
                    else JOptionPane.showMessageDialog(null, "You must log in first!");
                }
                case "4" -> {
                    if (loggedIn) displayReport();
                    else JOptionPane.showMessageDialog(null, "You must log in first!");
                }
                case "5" -> {
                    if (loggedIn) displayDoneTasks();
                    else JOptionPane.showMessageDialog(null, "You must log in first!");
                }
                case "6" -> {
                    if (loggedIn) displayLongestTaskDuration();
                    else JOptionPane.showMessageDialog(null, "You must log in first!");
                }
                case "7" -> {
                    if (loggedIn) searchTaskByName();
                    else JOptionPane.showMessageDialog(null, "You must log in first!");
                }
                case "8" -> {
                    if (loggedIn) searchTasksByDeveloper();
                    else JOptionPane.showMessageDialog(null, "You must log in first!");
                }
                case "9" -> {
                    if (loggedIn) deleteTaskByName();
                    else JOptionPane.showMessageDialog(null, "You must log in first!");
                }
                case "10" -> {
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    return;
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }
    }

    public void registerUser() {
        registeredUsername = JOptionPane.showInputDialog("Enter a username:");
        if (!checkUserName(registeredUsername)) {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted. It must contain an underscore and be no more than 5 characters.");
            return;
        }
        JOptionPane.showMessageDialog(null, "Username successfully captured.");

        registeredPassword = JOptionPane.showInputDialog("Enter a password:");
        if (!checkPasswordComplexity(registeredPassword)) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long and include a capital letter, a number, and a special character.");
            return;
        }
        JOptionPane.showMessageDialog(null, "Password successfully captured.");

        firstName = JOptionPane.showInputDialog("Enter your first name:");
        lastName = JOptionPane.showInputDialog("Enter your last name:");
    }

    public boolean loginUser() {
        String username = JOptionPane.showInputDialog("Enter your username:");
        String password = JOptionPane.showInputDialog("Enter your password:");

        if (username.equals(registeredUsername) && password.equals(registeredPassword)) {
            JOptionPane.showMessageDialog(null, "Welcome " + firstName + " " + lastName + ", it is great to see you again.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Username or password incorrect. Please try again.");
            return false;
        }
    }

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[!@#$%^&*()].*");
    }

    public void addTasks() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks to add:"));

        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter Task Name:");
            String taskDescription = JOptionPane.showInputDialog("Enter Task Description:");
            while (taskDescription.length() > 50) {
                taskDescription = JOptionPane.showInputDialog("Task description too long! Enter a description under 50 characters:");
            }

            String developer = JOptionPane.showInputDialog("Enter Developer Name:");
            int duration = Integer.parseInt(JOptionPane.showInputDialog("Enter Task Duration (hours):"));

            String statusMenu = """
                    1. To Do
                    2. Doing
                    3. Done
                    """;
            String statusChoice = JOptionPane.showInputDialog(statusMenu);
            String status = switch (statusChoice) {
                case "1" -> "To Do";
                case "2" -> "Doing";
                case "3" -> "Done";
                default -> "To Do";
            };

            addTask(developer, taskName, taskDescription, duration, status);
        }
    }

    public void addTask(String developer, String taskName, String taskDescription, int duration, String status) {
        developers[taskCount] = developer;
        taskNames[taskCount] = taskName;
        taskDurations[taskCount] = duration;
        taskStatuses[taskCount] = status;

        String taskID = taskName.substring(0, 2).toUpperCase() + ":" + taskCount + ":" +
                        developer.substring(developer.length() - 3).toUpperCase();
        taskIDs[taskCount] = taskID;

        JOptionPane.showMessageDialog(null, "Task successfully added! Task ID: " + taskID);
        taskCount++;
    }

    public void displayReport() {
        StringBuilder report = new StringBuilder("Task Report:\n\n");
        for (int i = 0; i < taskCount; i++) {
            report.append("Task ").append(i + 1).append(":\n")
                  .append("Developer: ").append(developers[i]).append("\n")
                  .append("Task Name: ").append(taskNames[i]).append("\n")
                  .append("Task ID: ").append(taskIDs[i]).append("\n")
                  .append("Duration: ").append(taskDurations[i]).append(" hrs\n")
                  .append("Status: ").append(taskStatuses[i]).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    public void displayDoneTasks() {
        StringBuilder doneTasks = new StringBuilder("Tasks marked as 'Done':\n\n");
        for (int i = 0; i < taskCount; i++) {
            if ("Done".equals(taskStatuses[i])) {
                doneTasks.append("Task Name: ").append(taskNames[i]).append("\n")
                         .append("Developer: ").append(developers[i]).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, doneTasks.toString());
    }

    public void displayLongestTaskDuration() {
        int maxDurationIndex = 0;
        for (int i = 1; i < taskCount; i++) {
            if (taskDurations[i] > taskDurations[maxDurationIndex]) {
                maxDurationIndex = i;
            }
        }
        JOptionPane.showMessageDialog(null,
                """
                Task with longest duration:
                Task Name: """ + taskNames[maxDurationIndex] + "\n" +
                "Developer: " + developers[maxDurationIndex] + "\n" +
                "Duration: " + taskDurations[maxDurationIndex] + " hours");
    }

    public void searchTaskByName() {
        String taskName = JOptionPane.showInputDialog("Enter the task name to search for:");
        for (int i = 0; i < taskCount; i++) {
            if (taskNames[i].equalsIgnoreCase(taskName)) {
                JOptionPane.showMessageDialog(null,
                        """
                        Task found:
                        Task Name: """ + taskNames[i] + "\n" +
                        "Developer: " + developers[i] + "\n" +
                        "Status: " + taskStatuses[i]);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    public void searchTasksByDeveloper() {
        String developerName = JOptionPane.showInputDialog("Enter the developer's name to search for tasks:");
        StringBuilder result = new StringBuilder("Tasks assigned to " + developerName + ":\n\n");
        for (int i = 0; i < taskCount; i++) {
            if (developers[i].equalsIgnoreCase(developerName)) {
                result.append("Task Name: ").append(taskNames[i]).append("\n")
                      .append("Status: ").append(taskStatuses[i]).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, result.toString());
    }

    public void deleteTaskByName() {
        String taskName = JOptionPane.showInputDialog("Enter the task name to delete:");
        for (int i = 0; i < taskCount; i++) {
            if (taskNames[i].equalsIgnoreCase(taskName)) {
                // Shift all tasks after the deleted task
                for (int j = i; j < taskCount - 1; j++) {
                    developers[j] = developers[j + 1];
                    taskNames[j] = taskNames[j + 1];
                    taskIDs[j] = taskIDs[j + 1];
                    taskDurations[j] = taskDurations[j + 1];
                    taskStatuses[j] = taskStatuses[j + 1];
                }
                taskCount--;
                JOptionPane.showMessageDialog(null, "Task deleted successfully.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.");
    }
}
