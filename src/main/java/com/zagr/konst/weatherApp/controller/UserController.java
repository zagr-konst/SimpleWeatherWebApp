package com.zagr.konst.weatherApp.controller;

import com.zagr.konst.weatherApp.model.User;
import com.zagr.konst.weatherApp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String createUser(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String creteUser(@Valid @ModelAttribute User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "register";
        System.out.println(user);
        userService.create(user);
        return "home";
    }

}
