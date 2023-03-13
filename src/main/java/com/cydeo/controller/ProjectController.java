package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

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
        model.addAttribute("managers",userService.findAll());
        model.addAttribute("projects",projectService.findAll());
        return "project/create";
    }

//    private List<UserDTO> findAllManagers(){
//     return   userService.findAll().stream().filter(u->u.getRole().equals("Manager")).map(e->e.getFirstName()+" "+e.getLastName()).collect(Collectors.toList());
//    }


    @PostMapping("/create")
    public String insert(@ModelAttribute("project") ProjectDTO projectDTO){
        projectService.save(projectDTO);
        return "redirect:/project/create";
    }
}
