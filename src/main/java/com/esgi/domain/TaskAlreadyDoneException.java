package com.esgi.domain;

public class TaskAlreadyDoneException extends RuntimeException {
    public TaskAlreadyDoneException() {
        super("Task already done");
    }
}
