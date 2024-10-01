import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleManager {
    private List<Task> tasks;
    private List<TaskObserver> observers;
    private static ScheduleManager instance = null;
    private static final Logger logger = Logger.getLogger(ScheduleManager.class.getName());

    private ScheduleManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    // Add Task Observer
    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    // Add Task
    public void addTask(String description, String startTime, String endTime, String priority) {
        try {
            Task task = TaskFactory.createTask(description, startTime, endTime, priority);
            if (hasConflict(task)) {
                notifyConflict(task);
            } else {
                tasks.add(task);
                notifyAdded(task);
                logger.log(Level.INFO, "Task added successfully: " + description);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add task: " + description, e);
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Edit Task
    public void editTask(String description, String newStartTime, String newEndTime, String newPriority) {
        Task task = findTaskByDescription(description);
        if (task != null) {
            task.setStartTime(newStartTime);
            task.setEndTime(newEndTime);
            task.setPriority(newPriority);
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Task not found.");
        }
    }

    // Mark Task as Completed
    public void markTaskAsCompleted(String description) {
        Task task = findTaskByDescription(description);
        if (task != null) {
            task.markAsCompleted();
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Task not found.");
        }
    }

    // Remove Task
    public void removeTask(String description) {
        Task toRemove = findTaskByDescription(description);
        if (toRemove != null) {
            tasks.remove(toRemove);
            notifyRemoved(toRemove);
            logger.log(Level.INFO, "Task removed successfully: " + description);
        } else {
            logger.log(Level.WARNING, "Task not found: " + description);
            System.out.println("Error: Task not found.");
        }
    }

    // View Tasks
    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled.");
            return;
        }
        tasks.sort(Comparator.comparing(Task::getStartTime));
        for (Task task : tasks) {
            task.display();
        }
    }

    // View Tasks by Priority
    public void viewTasksByPriority(String priority) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                task.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found with priority: " + priority);
        }
    }

    // Notify Observers of Conflict
    private void notifyConflict(Task task) {
        for (TaskObserver observer : observers) {
            observer.onTaskConflict(task);
        }
    }

    // Notify Observers of Task Addition
    private void notifyAdded(Task task) {
        for (TaskObserver observer : observers) {
            observer.onTaskAdded(task);
        }
    }

    // Notify Observers of Task Removal
    private void notifyRemoved(Task task) {
        for (TaskObserver observer : observers) {
            observer.onTaskRemoved(task);
        }
    }

    // Check for Task Conflicts
    private boolean hasConflict(Task newTask) {
        for (Task task : tasks) {
            if (!(newTask.getEndTime().compareTo(task.getStartTime()) <= 0 || newTask.getStartTime().compareTo(task.getEndTime()) >= 0)) {
                logger.log(Level.WARNING, "Conflict detected with task: " + task.getDescription());
                return true;
            }
        }
        return false;
    }

    // Find Task by Description
    private Task findTaskByDescription(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                return task;
            }
        }
        return null;
    }
}
