package com.esgi.domain.usecases;

import com.esgi.domain.Clock;
import com.esgi.domain.TaskRepository;
import com.esgi.domain.models.TaskList;
import com.esgi.domain.models.stubs.FakeClock;
import com.esgi.domain.models.stubs.FakeTaskRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddTaskTest {
    private TaskList taskList;
    private TaskRepository taskRepository;
    private Clock clock;

    @Before
    public void setUp() {
        taskRepository = new FakeTaskRepository();
        clock = new FakeClock();
        taskList = TaskList.empty(taskRepository);
    }

    @Test
    public void shouldAddTask() {
        AddTask addTask = new AddTask(taskList, taskRepository, clock);
        addTask.execute("title", "description");

        assertEquals(1, taskList.size());
        assertEquals("title", taskList.getAll().get(0).getTitle());
        assertEquals("description", taskList.getAll().get(0).getContent());
    }
}