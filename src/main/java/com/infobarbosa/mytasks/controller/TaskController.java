package com.infobarbosa.mytasks.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import com.infobarbosa.mytasks.entity.Task;
import com.infobarbosa.mytasks.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private TaskService service;

    @PostMapping
    public Task createTask(@RequestBody Task t){
        return service.createTask(t);
    };
    
    @PutMapping("/{id}")
    public Task updateTask(@RequestBody Task t, @PathVariable(value="id") String taskId){
        Task task = service.findTaskById(taskId);
        task.setDescription(t.getDescription());
        return service.updateTask(task);
    };

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable(value = "id") String taskId){
        service.deleteTask(taskId);
    }

    @GetMapping("/{id}")
    public Task findTaskById(@PathVariable(value = "id") String taskId){
        return service.findTaskById(taskId);
    }
    
    @GetMapping
    public List<Task> findAllTasks(){
        return service.findAllTasks();
    }
}
