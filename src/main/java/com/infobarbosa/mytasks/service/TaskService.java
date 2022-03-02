package com.infobarbosa.mytasks.service;

import java.util.List;

import com.infobarbosa.mytasks.entity.Task;
import com.infobarbosa.mytasks.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {

    public Task createTask(Task t);
    public Task updateTask(Task t);
    public void deleteTask(String taskId);
    public Task findTaskById(String taskId);
    public List<Task> findAllTasks();
}
