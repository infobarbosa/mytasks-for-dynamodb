package com.infobarbosa.mytasks.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.infobarbosa.mytasks.entity.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class TaskRepository {
    
    @Autowired
    private DynamoDBMapper mapper;

    public Task addTask(Task task){
        log.info("addTask chamado com argumento " + task);
        mapper.save(task);
        return task;
    }

    public Task findTaskById(String taskId){
        log.info("findTaskById chamado com argumento: " + taskId);
        return mapper.load(Task.class, taskId);
    }

    public void deleteTask(String taskId){
        log.info("deleteTask chamado com argumento: " + taskId);
        Task t = findTaskById(taskId);
        log.info("deletando task: " + t.toString());
        mapper.delete(t);
    }

    public void updateTask(Task task){
        log.info("updateTask chamado com argumento: " + task.toString());
        mapper.save(task, saveExpression(task));
    }

    private DynamoDBSaveExpression saveExpression(Task task) {
        log.info("saveExpression chamado com argumento: " + task.toString());

        return new DynamoDBSaveExpression()
            .withExpectedEntry("taskId", new ExpectedAttributeValue( new AttributeValue().withS(task.getTaskId())));
    }

    public List<Task> listAllTasks() {
        return mapper.scan(Task.class, scanExpression());
    }

    private DynamoDBScanExpression scanExpression() {
        return new DynamoDBScanExpression();
    }
}
