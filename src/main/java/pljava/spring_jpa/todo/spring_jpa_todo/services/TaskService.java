package pljava.spring_jpa.todo.spring_jpa_todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pljava.spring_jpa.todo.spring_jpa_todo.models.Task;
import pljava.spring_jpa.todo.spring_jpa_todo.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        List<Task> allTasksList = new ArrayList<>();
        Iterable<Task> iterator = taskRepository.findAll();
        iterator.forEach(allTasksList::add);
        return allTasksList;
    }

    public void saveTask(Task task) {
        if (task.getCloseDate() == null || task.getCloseDate().isBefore(task.getRegDate())) {
            task.setCloseDate(task.getRegDate());
        }

        taskRepository.save(task);
    }

    public Task getTask(int taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public void deleteTask(int taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
        }
    }

    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }

    public void doneStatusOfAllTasks() {
        List<Task> allTasksList = new ArrayList<>();
        Iterable<Task> iterator = taskRepository.findAll();
        iterator.forEach(task -> {
            task.setStatus(true);
            taskRepository.save(task);
        });
    }

    public void notDoneStatusOfAllTasks() {
        List<Task> allTasksList = new ArrayList<>();
        Iterable<Task> iterator = taskRepository.findAll();
        iterator.forEach(task -> {
            task.setStatus(false);
            taskRepository.save(task);
        });
    }

    public void doneStatusOfTaskById(int taskId) {
        if (taskRepository.existsById(taskId)) {
            Task task = taskRepository.findById(taskId).get();
            task.setStatus(true);
            taskRepository.save(task);
        }
    }

    public void notDoneStatusOfTaskById(int taskId) {
        if (taskRepository.existsById(taskId)) {
            Task task = taskRepository.findById(taskId).get();
            task.setStatus(false);
            taskRepository.save(task);
        }
    }

    public List<Task> getAllTasksOfStatus(boolean status) {
        return taskRepository.findAllByStatus(status);
    }
}
