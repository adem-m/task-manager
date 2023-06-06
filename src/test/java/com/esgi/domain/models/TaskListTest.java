package com.esgi.domain.models;

import com.esgi.domain.Clock;
import com.esgi.domain.TaskRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TaskListTest {
    private TaskRepository taskRepository;
    private Clock clock;
    private Clock laterClock;

    @Before
    public void setUp() {
        taskRepository = new FakeTaskRepository();
        clock = new FakeClock();
        laterClock = new LaterFakeClock();
    }

    @Test
    public void shouldAddToTaskList() {
        TaskList taskList = TaskList.empty(taskRepository);
        Task task = Task.of(clock, taskRepository, "title", "content");

        taskList.add(task);
        Optional<Task> optionalTask = taskList.get(task.getId());

        assertEquals(1, taskList.size());
        assertTrue(optionalTask.isPresent());
        assertEquals(task, optionalTask.get());
    }

    @Test
    public void shouldSortCorrectly() {
        TaskList taskList = TaskList.empty(taskRepository);
        Task task = Task.of(clock, taskRepository, "title", "content");
        Task laterTask = Task.of(laterClock, taskRepository, "title2", "content2");

        taskList.addAll(List.of(laterTask, task));
        taskList.sortByCreationDate();

        assertEquals(2, taskList.size());

        List<Task> tasks = taskList.getAll();

        assertEquals(task, tasks.get(0));
        assertEquals(laterTask, tasks.get(1));
    }

    @Test
    public void shouldRemoveFromTaskList() {
        TaskList taskList = TaskList.empty(taskRepository);
        Task task = Task.of(clock, taskRepository, "title", "content");

        taskList.add(task);
        taskList.remove(task.getId());

        assertEquals(0, taskList.size());
    }
}
