package com.esgi.domain.usecases;

import com.esgi.domain.TaskRepository;
import com.esgi.domain.models.TaskList;

public class CreateTaskList {
    private final TaskRepository taskRepository;

    public CreateTaskList(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskList execute() {
        return TaskList.empty(taskRepository);
    }
}
