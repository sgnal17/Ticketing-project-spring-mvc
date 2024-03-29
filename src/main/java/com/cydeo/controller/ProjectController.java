package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;

import com.cydeo.dto.UserDTO;
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
    public String create(Model model) {
        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("managers", userService.findManagers());
        model.addAttribute("projects", projectService.findAll());
        return "project/create";
    }

    @PostMapping("/create")
    public String insert(@ModelAttribute("project") ProjectDTO projectDTO) {
        projectService.save(projectDTO);
        return "redirect:/project/create";
    }


    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") String id) {
        projectService.deleteById(id);

        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectCode}")
    public String completeProject(@PathVariable("projectCode") String projectCode) {
        //complete-status is going to change to completed
        projectService.complete(projectService.findById(projectCode));
        return "redirect:/project/create";
    }

    @GetMapping("/update/{projectCode}")
    public String updateProject(@PathVariable("projectCode") String projectCode,Model model){
        model.addAttribute("project",projectService.findById(projectCode));
        model.addAttribute("managers",userService.findManagers());
        model.addAttribute("projects",projectService.findAll());
        return "project/update";
    }

    @PostMapping("/update")
    public String updateProject(@ModelAttribute("project") ProjectDTO projectDTO){
        projectService.save(projectDTO);
        return "redirect:/project/create";
    }

    @GetMapping("/manager/project-status")
    public String getProjectsByManager(Model model){
//        UserDTO manager=userService.findById("john@cydeo.com");
//
//        model.addAttribute("projects",projectService.getCountedListOfProjectDto(manager));


        return "manager/project-status";
    }

}
