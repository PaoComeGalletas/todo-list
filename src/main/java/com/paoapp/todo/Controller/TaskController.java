package com.paoapp.todo.Controller;

import com.paoapp.todo.Model.Task;
import com.paoapp.todo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.save(task), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/task")
    public ResponseEntity<?> changeStatus(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(taskService.changeStatus(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Task not found",  HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/task")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try{
            return new ResponseEntity<>(taskService.findById(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Task not found",  HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/task")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            taskService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Task not found",  HttpStatus.BAD_REQUEST);
        }
    }
}
