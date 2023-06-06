package com.esgi.domain;

import com.esgi.domain.models.Task;
import com.esgi.domain.models.TaskList;

import java.util.Optional;

public interface TaskRepository {
    String nextId();

    void save(Task task);

    void remove(Task task);

    Optional<Task> get(String id);

    TaskList getAll();
}
