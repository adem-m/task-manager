package com.esgi.domain.usecases;

import com.esgi.domain.Clock;
import com.esgi.domain.TaskRepository;
import com.esgi.domain.models.Status;
import com.esgi.domain.models.Task;
import com.esgi.domain.models.TaskList;
import com.esgi.domain.models.stubs.FakeClock;
import com.esgi.domain.models.stubs.FakeTaskRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UpdateTaskStatusTest {
    private TaskRepository taskRepository;
    private TaskList taskList;
    private Clock clock;

    @Before
    public void setUp() {
        taskRepository = new FakeTaskRepository();
        taskList = TaskList.empty(taskRepository);
        clock = new FakeClock();
    }

    @Test
    public void shouldUpdateTaskStatusToCancelled() {
        UpdateTaskStatus updateTaskStatus = new UpdateTaskStatus(taskRepository, taskList);
        AddTask addTask = new AddTask(taskList, taskRepository, clock);
        addTask.execute("title", "description");

        Optional<Task> task = taskList.get("id");
        assertTrue(task.isPresent());

        updateTaskStatus.execute(task.get().getId(), Status.CANCELLED);

        assertEquals(Status.CANCELLED, task.get().getStatus());
    }
}