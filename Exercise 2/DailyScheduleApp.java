import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DailyScheduleApp implements TaskObserver {
    private static Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(DailyScheduleApp.class.getName());

    public static void main(String[] args) {
        DailyScheduleApp app = new DailyScheduleApp();
        ScheduleManager manager = ScheduleManager.getInstance();
        manager.addObserver(app);
        boolean running = true;

        while (running) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Edit Task");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. View All Tasks");
            System.out.println("6. View Tasks by Priority");
            System.out.println("7. Exit");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Invalid input format for choice", e);
                System.out.println("Invalid input. Please enter a valid number.");
            }

            switch (choice) {
                case 1:
                    addTask(manager);
                    break;
                case 2:
                    removeTask(manager);
                    break;
                case 3:
                    editTask(manager);
                    break;
                case 4:
                    markTaskAsCompleted(manager);
                    break;
                case 5:
                    viewTasks(manager);
                    break;
                case 6:
                    viewTasksByPriority(manager);
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addTask(ScheduleManager manager) {
        System.out.println("Enter task description:");
        String description = scanner.nextLine();
        System.out.println("Enter task start time (HH:mm):");
        String startTime = scanner.nextLine();
        System.out.println("Enter task end time (HH:mm):");
        String endTime = scanner.nextLine();
        System.out.println("Enter task priority (Low, Medium, High):");
        String priority = scanner.nextLine();

        manager.addTask(description, startTime, endTime, priority);
    }

    private static void removeTask(ScheduleManager manager) {
        System.out.println("Enter task description to remove:");
        String description = scanner.nextLine();
        manager.removeTask(description);
    }

    private static void editTask(ScheduleManager manager) {
        System.out.println("Enter task description to edit:");
        String description = scanner.nextLine();
        System.out.println("Enter new start time (HH:mm):");
        String startTime = scanner.nextLine();
        System.out.println("Enter new end time (HH:mm):");
        String endTime = scanner.nextLine();
        System.out.println("Enter new priority (Low, Medium, High):");
        String priority = scanner.nextLine();

        manager.editTask(description, startTime, endTime, priority);
    }

    private static void markTaskAsCompleted(ScheduleManager manager) {
        System.out.println("Enter task description to mark as completed:");
        String description = scanner.nextLine();
        manager.markTaskAsCompleted(description);
    }

    private static void viewTasks(ScheduleManager manager) {
        manager.viewTasks();
    }

    private static void viewTasksByPriority(ScheduleManager manager) {
        System.out.println("Enter priority to view (Low, Medium, High):");
        String priority = scanner.nextLine();
        manager.viewTasksByPriority(priority);
    }

    @Override
    public void onTaskConflict(Task task) {
        System.out.println("Error: Task conflicts with existing task " + task.getDescription());
    }

    @Override
    public void onTaskAdded(Task task) {
        System.out.println("Task added successfully: " + task.getDescription());
    }

    @Override
    public void onTaskRemoved(Task task) {
        System.out.println("Task removed successfully: " + task.getDescription());
    }
}
