package com.esgi.domain.usecases;

import com.esgi.domain.TaskRepository;
import com.esgi.domain.models.TaskList;
import com.esgi.domain.models.stubs.FakeTaskRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateTaskListTest {
    private TaskRepository taskRepository;

    @Before
    public void setUp() {
        taskRepository = new FakeTaskRepository();
    }

    @Test
    public void shouldCreateTaskList() {
        CreateTaskList createTaskList = new CreateTaskList(taskRepository);
        TaskList taskList = createTaskList.execute();

        assertNotNull(taskList);
        assertEquals(0, taskList.size());
    }
}