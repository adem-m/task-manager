package com.esgi.domain.models;

import com.esgi.domain.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeTaskRepository implements TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public String nextId() {
        return "id";
    }

    @Override
    public void save(Task task) {
        tasks.add(task);
    }

    @Override
    public void remove(Task task) {
        tasks.remove(task);
    }

    @Override
    public Optional<Task> get(String id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst();
    }

    @Override
    public TaskList getAll() {
        TaskList taskList = TaskList.empty(this);
        taskList.addAll(tasks);
        return taskList;
    }
}
