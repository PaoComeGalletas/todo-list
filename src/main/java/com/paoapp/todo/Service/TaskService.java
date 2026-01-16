package com.paoapp.todo.Service;

import com.paoapp.todo.Model.Status;
import com.paoapp.todo.Model.Task;
import com.paoapp.todo.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task save(Task task) {
        task.setStatus(Status.PENDING);
        return taskRepository.save(task);
    }

    public Task changeStatus(Long id) {
        Task task = findById(id);
        if(task.getStatus().equals(Status.PENDING))
            task.setStatus(Status.IN_PROGRESS);
        else
            if(task.getStatus().equals(Status.IN_PROGRESS))
                task.setStatus(Status.COMPLETED);
        return taskRepository.save(task);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task no encontrado"));
    }

    public void remove(Long id) {
        taskRepository.delete(findById(id));
    }
}
