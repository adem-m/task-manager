package com.esgi.domain.usecases;

import com.esgi.domain.TaskRepository;
import com.esgi.domain.models.TaskList;

public class AddTask {
    private final TaskRepository taskRepository;

    public AddTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void execute(TaskList taskList, String title, String content) {

    }
}
