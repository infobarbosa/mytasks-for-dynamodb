package com.infobarbosa.mytasks.service;

import java.util.List;

import com.infobarbosa.mytasks.entity.Task;
import com.infobarbosa.mytasks.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;

    public Task createTask(Task t){ 
        log.info("createTask chamado com argumento " + t);
        repository.addTask(t);
        return t;
    };

    public Task updateTask(Task t){  
        repository.updateTask(t);
        return t;
    }
    
    public void deleteTask(String taskId){  
        repository.deleteTask(taskId);
    }

    public Task findTaskById(String taskId){ 
        return repository.findTaskById(taskId);
    }

    public List<Task> findAllTasks(){  
        return repository.listAllTasks();
    }
}
