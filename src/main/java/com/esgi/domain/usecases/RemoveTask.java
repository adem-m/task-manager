package com.esgi.domain.usecases;

import com.esgi.domain.exceptions.TaskNotFoundException;
import com.esgi.domain.TaskRepository;
import com.esgi.domain.models.Task;
import com.esgi.domain.models.TaskList;

import java.util.Optional;

public class RemoveTask {
    private final TaskRepository taskRepository;

    public RemoveTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void execute(TaskList taskList, String id) {
        Optional<Task> task = taskRepository.get(id);
        if (task.isEmpty()) {
            throw new TaskNotFoundException(String.format("Task with id %s not found", id));
        }
        taskList.remove(id);
        taskRepository.remove(task.get());
    }
}
