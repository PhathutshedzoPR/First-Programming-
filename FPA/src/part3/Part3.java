/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package part3;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Part3 {
    private List<String> developerArray;
    private List<String> taskNamesArray;
    private List<String> taskIDArray;
    private List<String> taskDurationArray;
    List<String> taskStatusArray;

    public Part3() {
        developerArray = new ArrayList<>();
        taskNamesArray = new ArrayList<>();
        taskIDArray = new ArrayList<>();
        taskDurationArray = new ArrayList<>();
        taskStatusArray = new ArrayList<>();
    }

    public void populateArrays() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks:"));

        for (int i = 0; i < numTasks; i++) {
            developerArray.add(JOptionPane.showInputDialog("Enter the developer for task #" + (i + 1) + ":"));
            taskNamesArray.add(JOptionPane.showInputDialog("Enter the task name for task #" + (i + 1) + ":"));
            taskIDArray.add(generateTaskID());
            taskDurationArray.add(JOptionPane.showInputDialog("Enter the task duration (in hours) for task #" + (i + 1) + ":"));
            taskStatusArray.add(JOptionPane.showInputDialog("Enter the task status (To do / Done / doing) for task #" + (i + 1) + ":"));
        }
    }

    private String generateTaskID() {
        // Generate a unique task ID (e.g., using UUID or any other suitable method)
        // For simplicity, let's assume the task ID is a sequential number
        int taskID = taskIDArray.size() + 1;
        return String.valueOf(taskID);
    }

    public void displayTasksWithStatus(String status) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < taskStatusArray.size(); i++) {
            if (taskStatusArray.get(i).equalsIgnoreCase(status)) {
                output.append("Developer: ").append(developerArray.get(i))
                        .append(", Task Name: ").append(taskNamesArray.get(i))
                        .append(", Task Duration: ").append(taskDurationArray.get(i)).append("\n");
            }
        }

        if (output.length() > 0) {
            JOptionPane.showMessageDialog(null, output.toString(), "Tasks with Status: " + status, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found with the status: " + status, "No Tasks Found", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void displayLongestTask() {
        int longestTaskIndex = 0;
        int longestDuration = Integer.MIN_VALUE;

        for (int i = 0; i < taskDurationArray.size(); i++) {
            int duration = Integer.parseInt(taskDurationArray.get(i));
            if (duration > longestDuration) {
                longestDuration = duration;
                longestTaskIndex = i;
            }
        }

        JOptionPane.showMessageDialog(null, "Developer: " + developerArray.get(longestTaskIndex) +
                ", Task Duration: " + taskDurationArray.get(longestTaskIndex), "Longest Task", JOptionPane.INFORMATION_MESSAGE);
    }

    public void searchTaskByName(String taskName) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < taskNamesArray.size(); i++) {
            if (taskNamesArray.get(i).equalsIgnoreCase(taskName)) {
                output.append("Task Name: ").append(taskNamesArray.get(i))
                        .append(", Developer: ").append(developerArray.get(i))
                        .append(", Task Status: ").append(taskStatusArray.get(i)).append("\n");
            }
        }

        if (output.length() > 0) {
            JOptionPane.showMessageDialog(null, output.toString(), "Search Result for Task Name: " + taskName, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found with the name: " + taskName, "No Tasks Found", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void searchTasksByDeveloper(String developer) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < developerArray.size(); i++) {
            if (developerArray.get(i).equalsIgnoreCase(developer)) {
                output.append("Task Name: ").append(taskNamesArray.get(i))
                        .append(", Task Status: ").append(taskStatusArray.get(i)).append("\n");
            }
        }

        if (output.length() > 0) {
            JOptionPane.showMessageDialog(null, output.toString(), "Search Result for Developer: " + developer, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found for the developer: " + developer, "No Tasks Found", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void deleteTaskByName(String taskName) {
        for (int i = 0; i < taskNamesArray.size(); i++) {
            if (taskNamesArray.get(i).equalsIgnoreCase(taskName)) {
                developerArray.remove(i);
                taskNamesArray.remove(i);
                taskIDArray.remove(i);
                taskDurationArray.remove(i);
                taskStatusArray.remove(i);
                JOptionPane.showMessageDialog(null, "Task '" + taskName + "' deleted successfully!", "Task Deletion", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Task not found!", "Task Deletion", JOptionPane.WARNING_MESSAGE);
    }

    public void displayTaskReport() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < taskNamesArray.size(); i++) {
            output.append("Task ID: ").append(taskIDArray.get(i))
                    .append(", Task Name: ").append(taskNamesArray.get(i))
                    .append(", Developer: ").append(developerArray.get(i))
                    .append(", Task Duration: ").append(taskDurationArray.get(i))
                    .append(", Task Status: ").append(taskStatusArray.get(i)).append("\n");
        }

        JOptionPane.showMessageDialog(null, output.toString(), "Task Report", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        Part3 taskManager = new Part3();
        taskManager.populateArrays();

        // Menu options
        String[] options = {
                "Display Tasks with Status",
                "Display Longest Task",
                "Search Task by Name",
                "Search Tasks by Developer",
                "Delete Task by Name",
                "Display Task Report",
                "Exit"
        };

        while (true) {
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Select an option:",
                    "Task Manager",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice) {
                case 0:
                    String status = JOptionPane.showInputDialog("Enter the task status (To do / Done / doing):");
                    taskManager.displayTasksWithStatus(status);
                    break;
                case 1:
                    taskManager.displayLongestTask();
                    break;
                case 2: {
                    String taskName = JOptionPane.showInputDialog("Enter the task name to search:");
                    taskManager.searchTaskByName(taskName);
                    break;
                }
                case 3: {
                    String developer = JOptionPane.showInputDialog("Enter the developer name to search tasks:");
                    taskManager.searchTasksByDeveloper(developer);
                    break;
                }
                case 4: {
                    String taskToDelete = JOptionPane.showInputDialog("Enter the task name to delete:");
                    taskManager.deleteTaskByName(taskToDelete);
                    break;
                }
                case 5:
                    taskManager.displayTaskReport();
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Exiting the Task Manager...", "Exit", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
            }
        }
    }
}
