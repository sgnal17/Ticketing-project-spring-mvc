package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;

    public TaskController(TaskService taskService, UserService userService, ProjectService projectService) {
        this.taskService = taskService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String createTask(Model model) {
        model.addAttribute("task", new TaskDTO());
        model.addAttribute("taskList", taskService.findAll());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        return "task/create";
    }

    @PostMapping("/create")
    public String saveTask(@ModelAttribute("task") TaskDTO taskDTO) {

        taskService.save(taskDTO);
        return "redirect:/task/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteById(id);
        return "redirect:/task/create";
    }

    @GetMapping("/update/{id}")
    public String editTask(@PathVariable("id") Long id, Model model) {
        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("taskList", taskService.findAll());

        return "task/update";
    }
//      First way
//    @PostMapping("/update/{id}")
//    public String updateTask(@PathVariable("id") Long id,@ModelAttribute("task") TaskDTO taskDTO){
//        taskDTO.setId(id);
//        taskService.update(taskDTO);
//        return "redirect:/task/create";
//    }

    //   Second way
    @PostMapping("/update/{id}") // Spring understand if this id field name and objects field's name is same
    public String updateTask(TaskDTO taskDTO) {
        taskService.update(taskDTO);        // it is going to set id automatically
        return "redirect:/task/create";
    }

    @GetMapping("/pending")
    public String getPendingTasks(Model model){
        model.addAttribute("taskList",taskService.findAllTasksByIsNot(Status.COMPLETE));


        return "task/pending-tasks";
    }


}
