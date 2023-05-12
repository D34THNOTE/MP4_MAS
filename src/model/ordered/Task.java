package model.ordered;

import java.time.LocalDateTime;
import java.util.*;

public class Task {
    private static SortedSet<Task> extent = new TreeSet<>(Comparator.comparing(Task::getCreatedAt));

    private String title, description;

    private LocalDateTime createdAt;


    public Task(String title, String description) {
        setTitle(title);
        setDescription(description);
        setCreatedAt();

        extent.add(this);
    }

    public static SortedSet<Task> getExtent() {
        return Collections.unmodifiableSortedSet(extent);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title == null || title.isBlank()) throw new IllegalArgumentException("Task's title is required");

        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description == null || description.isBlank()) throw new IllegalArgumentException("Task's description is required");

        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private void setCreatedAt() { // private
        this.createdAt = LocalDateTime.now();
    }
}
