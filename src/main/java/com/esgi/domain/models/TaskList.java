package com.esgi.domain.models;

import com.esgi.domain.TaskRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TaskList {
    private final TaskRepository taskRepository;
    private final List<Task> tasks;

    private TaskList(TaskRepository taskRepository, List<Task> tasks) {
        this.taskRepository = taskRepository;
        this.tasks = tasks;
    }

    public static TaskList empty(TaskRepository taskRepository) {
        return new TaskList(taskRepository, new ArrayList<>());
    }

    public void add(Task task) {
        tasks.add(task);
        taskRepository.save(task);
    }

    public void addAll(List<Task> tasks) {
        this.tasks.addAll(tasks);
        tasks.forEach(taskRepository::save);
    }

    public Optional<Task> get(String id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    public List<Task> getAll() {
        return tasks;
    }

    public void sortByCreationDate() {
        tasks.sort(Comparator.comparing(Task::getCreationDate));
    }

    public void remove(String id) {
        tasks.removeIf(task -> task.getId().equals(id));
        taskRepository.remove(get(id).get());
    }

    public int size() {
        return tasks.size();
    }
}
