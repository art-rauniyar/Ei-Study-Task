import java.io.Serializable;

public class Task implements Serializable {
    private String description;
    private String startTime;
    private String endTime;
    private String priority;
    private boolean completed;

    public Task(String description, String startTime, String endTime, String priority) {
        if (!validateTime(startTime) || !validateTime(endTime)) {
            throw new IllegalArgumentException("Invalid time format");
        }
        if (!validatePriority(priority)) {
            throw new IllegalArgumentException("Invalid priority value");
        }
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.completed = false;
    }

    // Getters and Setters
    public String getDescription() { return description; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getPriority() { return priority; }
    public boolean isCompleted() { return completed; }

    public void setDescription(String description) { this.description = description; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
    public void setPriority(String priority) { this.priority = priority; }
    public void markAsCompleted() { this.completed = true; }

    public void display() {
        System.out.println(startTime + " - " + endTime + ": " + description + " [" + priority + "] " 
                           + (completed ? "(Completed)" : ""));
    }

    // Time and Priority Validators
    private boolean validateTime(String time) {
        return time.matches("([01]\\d|2[0-3]):[0-5]\\d");
    }

    private boolean validatePriority(String priority) {
        return priority.equalsIgnoreCase("Low") || priority.equalsIgnoreCase("Medium") || priority.equalsIgnoreCase("High");
    }
}
