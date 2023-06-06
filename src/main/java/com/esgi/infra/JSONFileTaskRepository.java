package com.esgi.infra;

import com.esgi.domain.TaskRepository;
import com.esgi.domain.models.Task;
import com.esgi.domain.models.TaskList;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.util.Optional;

public class JSONFileTaskRepository implements TaskRepository {
    private final static String FILENAME = "tasks.json";
    private final static File FILE = new File(FILENAME);
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String nextId() {
        return String.valueOf(getAll().size() + 1);
    }

    @Override
    public void save(Task task) {
        try {
            mapper.writeValue(FILE, task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Task task) {
        TaskList taskList = getAll();
        taskList.remove(task.getId());
        try {
            mapper.writeValue(FILE, taskList);
        } catch (Exception ignored) {
        }
    }

    @Override
    public Optional<Task> get(String id) {
        TaskList taskList = getAll();
        return taskList.get(id);
    }

    @Override
    public TaskList getAll() {
        try {
            FileReader reader = new FileReader(FILE);
            return mapper.readValue(reader, TaskList.class);
        } catch (Exception e) {
            return null;
        }
    }
}
