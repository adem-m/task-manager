package com.esgi.domain.exceptions;

public class TaskAlreadyDoneException extends RuntimeException {
    public TaskAlreadyDoneException() {
        super("Task already done");
    }
}
