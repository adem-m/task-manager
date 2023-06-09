package com.esgi.domain.models.models;

import com.esgi.domain.Clock;
import com.esgi.domain.exceptions.TaskAlreadyDoneException;
import com.esgi.domain.TaskRepository;
import com.esgi.domain.models.Status;
import com.esgi.domain.models.Task;
import com.esgi.domain.models.stubs.FakeClock;
import com.esgi.domain.models.stubs.FakeTaskRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {
    private Clock clock;
    private TaskRepository taskRepository;

    @Before
    public void setUp() {
        clock = new FakeClock();
        taskRepository = new FakeTaskRepository();
    }

    @Test
    public void simpleTaskCreation() {
        Task task = Task.of(clock, taskRepository, "title", "content");

        assertEquals("title", task.getTitle());
        assertEquals("content", task.getContent());
        Assert.assertEquals(Status.TODO, task.getStatus());
        assertEquals(clock.now(), task.getCreationDate());
        assertNull(task.getDueDate());
        assertNull(task.getCloseDate());
        assertEquals("id", task.getId());
        assertEquals(0, task.getSubTasks().size());
    }

    @Test
    public void shouldCloseTask() {
        Task task = Task.of(clock, taskRepository, "title", "content");
        task.updateStatus(Status.DONE);

        assertEquals(Status.DONE, task.getStatus());
        assertEquals(clock.now(), task.getCloseDate());
    }

    @Test
    public void shouldUpdateStatusToPending() {
        Task task = Task.of(clock, taskRepository, "title", "content");
        task.updateStatus(Status.PENDING);

        assertEquals(Status.PENDING, task.getStatus());
        assertNull(task.getCloseDate());
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingStatusToProgressWhenClosed() {
        Task task = Task.of(clock, taskRepository, "title", "content");
        task.updateStatus(Status.DONE);

        assertThrows(TaskAlreadyDoneException.class, () -> {
            task.updateStatus(Status.PROGRESS);
        });
    }
}