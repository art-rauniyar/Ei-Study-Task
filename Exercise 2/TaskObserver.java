public interface TaskObserver {
    void onTaskConflict(Task task);
    void onTaskAdded(Task task);
    void onTaskRemoved(Task task);
}
