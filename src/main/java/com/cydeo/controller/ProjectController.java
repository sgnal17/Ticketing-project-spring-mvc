package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;

import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/project")
public class ProjectController {
    private final UserService userService;
    private final ProjectService projectService;

    public ProjectController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("project",new ProjectDTO());
        model.addAttribute("managers",userService.findManagers());
        model.addAttribute("projects",projectService.findAll());
        return "project/create";
    }

    @PostMapping("/create")
    public String insert(@ModelAttribute("project") ProjectDTO projectDTO){
        projectService.save(projectDTO);
        return "redirect:/project/create";
    }


    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") String id ){
        projectService.deleteById(id);

        return "redirect:/project/create";
    }

}
