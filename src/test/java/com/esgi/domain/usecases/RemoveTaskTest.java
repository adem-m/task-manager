package com.esgi.domain.usecases;

import com.esgi.domain.Clock;
import com.esgi.domain.TaskNotFoundException;
import com.esgi.domain.TaskRepository;
import com.esgi.domain.models.Task;
import com.esgi.domain.models.TaskList;
import com.esgi.domain.models.stubs.FakeClock;
import com.esgi.domain.models.stubs.FakeTaskRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class RemoveTaskTest {
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
    public void shouldRemoveTask() {
        RemoveTask removeTask = new RemoveTask(taskRepository);
        AddTask addTask = new AddTask(taskList, taskRepository, clock);
        addTask.execute("title", "description");

        Task task = taskList.getAll().get(0);
        assertEquals(1, taskList.size());

        removeTask.execute(taskList, task.getId());

        assertEquals(0, taskList.size());
    }

    @Test
    public void shouldThrowExceptionWhenTaskDoesNotExist() {
        RemoveTask removeTask = new RemoveTask(taskRepository);
        AddTask addTask = new AddTask(taskList, taskRepository, clock);
        addTask.execute("title", "description");

        assertThrows(TaskNotFoundException.class, () -> {
            removeTask.execute(taskList, "toto");
        });
    }
}