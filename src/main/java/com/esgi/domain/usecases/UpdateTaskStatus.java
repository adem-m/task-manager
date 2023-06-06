package com.esgi.domain.usecases;

import com.esgi.domain.TaskRepository;
import com.esgi.domain.models.Status;
import com.esgi.domain.models.Task;
import com.esgi.domain.models.TaskList;

import java.util.Optional;

public class UpdateTaskStatus {
    private final TaskRepository taskRepository;
    private final TaskList taskList;

    public UpdateTaskStatus(TaskRepository taskRepository, TaskList taskList) {
        this.taskRepository = taskRepository;
        this.taskList = taskList;
    }

    public void execute(String id, Status status) {
        Optional<Task> task = taskList.get(id);
        if (task.isEmpty()) {
            return;
        }

        task.get().updateStatus(status);
        taskRepository.save(task.get());
    }
}
