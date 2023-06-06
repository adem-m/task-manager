package com.esgi.domain.usecases;

import com.esgi.domain.TaskRepository;
import com.esgi.domain.models.Status;
import com.esgi.domain.models.Task;

import java.util.Optional;

public class UpdateTaskStatus {
    private final TaskRepository taskRepository;

    public UpdateTaskStatus(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void execute(String id, Status status) {
        Optional<Task> task = taskRepository.get(id);
        if (task.isEmpty()) {
            return;
        }

        task.get().updateStatus(status);
        taskRepository.save(task.get());
    }
}
