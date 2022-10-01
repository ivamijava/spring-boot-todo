package pljava.spring_jpa.todo.spring_jpa_todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pljava.spring_jpa.todo.spring_jpa_todo.models.Task;

import pljava.spring_jpa.todo.spring_jpa_todo.services.TaskService;

@Controller
@RequestMapping("/")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public String showAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "index-form";
    }

    @GetMapping("/{id}")
    public String showTaskById(@PathVariable("id") int taskId,
                               Model model) {
        model.addAttribute("task", taskService.getTask(taskId));
        return "show-form";
    }

    @GetMapping("/new")
    public String addNewTask(Model model) {
        model.addAttribute("task", new Task());
        return "new-form";
    }

    @PostMapping("/new")
    public String createNewTask(@ModelAttribute("task") Task task) {
        taskService.saveTask(task);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable("id") int taskId,
                               Model model) {
        model.addAttribute("task", taskService.getTask(taskId));
        return "edit-form";
    }

    @PostMapping("/edit")
    public String editTask(@ModelAttribute("task") Task task) {
        taskService.saveTask(task);
        return "redirect:/" + task.getId();
    }

    @GetMapping("/delete/all")
    public String deleteAllTasks() {
        taskService.deleteAllTasks();
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTaskById(@PathVariable("id") int taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/";
    }

    @GetMapping("/set_done")
    public String setDoneStatusToAllTasks() {
        taskService.doneStatusOfAllTasks();
        return "redirect:/";
    }

    @GetMapping("/set_not_done")
    public String setNotDoneStatusToAllTasks() {
        taskService.notDoneStatusOfAllTasks();
        return "redirect:/";
    }

    @GetMapping("/{id}/set_done")
    public String setDoneStatusById(@PathVariable("id") int taskId) {
        taskService.doneStatusOfTaskById(taskId);
        return "redirect:/" + taskId;
    }

    @GetMapping("/{id}/set_not_done")
    public String setNotDoneStatusById(@PathVariable("id") int taskId) {
        taskService.notDoneStatusOfTaskById(taskId);
        return "redirect:/" + taskId;
    }

    @GetMapping("/done_status")
    public String getAllDoneTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasksOfStatus(true));
        return "index-form";
    }

    @GetMapping("/not_done_status")
    public String getAllNotDoneTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasksOfStatus(false));
        return "index-form";
    }


}
