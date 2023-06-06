package com.esgi.domain.models;

import com.esgi.domain.Clock;
import com.esgi.domain.TaskAlreadyDoneException;
import com.esgi.domain.TaskRepository;

import java.time.LocalDate;
import java.util.Objects;

public class Task {
    private final Clock clock;

    private final String id;
    private final String title;
    private final String content;
    private final LocalDate creationDate;
    private LocalDate dueDate;
    private LocalDate closeDate;
    private Status status;
    private final TaskList subTasks;

    private Task(Clock clock, String id, String title, String content, LocalDate creationDate, LocalDate dueDate, LocalDate closeDate, Status status, TaskList subTasks) {
        this.clock = clock;
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.closeDate = closeDate;
        this.status = status;
        this.subTasks = subTasks;
    }

    public static Task of(Clock clock, TaskRepository taskRepository, String title, String content) {
        return new Task(
                clock,
                taskRepository.nextId(),
                title,
                content,
                clock.now(),
                null,
                null,
                Status.TODO,
                TaskList.empty(taskRepository)
        );
    }

    public void updateStatus(Status status) {
        if (this.status == Status.DONE) {
            throw new TaskAlreadyDoneException();
        }
        if (status == Status.DONE) {
            this.close();
        } else {
            this.status = status;
        }
    }

    private void close() {
        this.closeDate = clock.now();
        this.status = Status.DONE;
    }

    public String getId() {
        return id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public TaskList getSubTasks() {
        return subTasks;
    }
}
