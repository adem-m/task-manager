package com.esgi.domain.models;

import com.esgi.domain.TaskRepository;

import java.util.Optional;

public class FakeTaskRepository implements TaskRepository {
    private final TaskList taskList = TaskList.empty(this);

    @Override
    public String nextId() {
        return "id";
    }

    @Override
    public void save(Task task) {
        taskList.add(task);
    }

    @Override
    public void remove(Task task) {
        taskList.remove(task.getId());
    }

    @Override
    public Optional<Task> get(String id) {
        return taskList.get(id);
    }

    @Override
    public TaskList getAll() {
        return taskList;
    }
}
