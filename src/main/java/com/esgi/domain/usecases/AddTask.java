package com.esgi.domain.usecases;

import com.esgi.domain.Clock;
import com.esgi.domain.TaskRepository;
import com.esgi.domain.models.Task;
import com.esgi.domain.models.TaskList;

public class AddTask {
    private final TaskList taskList;
    private final TaskRepository taskRepository;
    private final Clock clock;

    public AddTask(TaskList taskList, TaskRepository taskRepository, Clock clock) {
        this.taskList = taskList;
        this.taskRepository = taskRepository;
        this.clock = clock;
    }

    public void execute(String title, String content) {
        Task task = Task.of(clock, taskRepository, title, content);
        taskList.add(task);
    }
}
