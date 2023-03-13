package com.cydeo.controller;


import com.cydeo.dto.UserDTO;


import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("users",userService.findAll());
        return "/user/create";
    }

    @PostMapping("/add")
    public String insertUser(@ModelAttribute("user") UserDTO user, Model model){
        userService.save(user);

        return "redirect:/user/create";
    }

//UPDATE
    @GetMapping("/update/{username}")
    public String editUser(@PathVariable("username") String username, Model model){

        model.addAttribute("user",userService.findById(username));
        model.addAttribute("roles",roleService.findAll());
        model.addAttribute("users",userService.findAll());

        return "user/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") UserDTO userDTO){
        userService.update(userDTO);
        return "redirect:/user/create";
    }

  //DELETE

  @GetMapping("/delete/{username}")
  public String delete(@PathVariable("username") String username){
        userService.deleteById(username);

        return "redirect:/user/create";
  }

}
