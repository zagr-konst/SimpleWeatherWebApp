package com.zagr.konst.weatherApp.controller;

import com.zagr.konst.weatherApp.controller.parse.MyJsonParser;
import com.zagr.konst.weatherApp.model.City;
import com.zagr.konst.weatherApp.model.User;
import com.zagr.konst.weatherApp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    public String creteUser(@Valid @ModelAttribute User user, BindingResult bindingResult
                ,Model model){
        if (bindingResult.hasErrors()) return "register";
        System.out.println(user);
        userService.create(user);
        model.addAttribute("cityObj", new City());
        model.addAttribute("chosenCity",new City());
        return "choose-city";
    }

    @GetMapping("/selectCity")
    public ModelAndView select() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cityObj", new City());
        modelAndView.addObject("chosenCity", new City());
        modelAndView.setViewName("choose-city");
        return modelAndView;
    }

    @PostMapping("/selectCity")
    public ModelAndView select(@ModelAttribute("chosenCity") City city, Model model) {
        System.out.println(city);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id",city.getCityID());
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }


    @PostMapping("/findCity")
    public String findCity(@ModelAttribute City city,Model model){
        List<City> cityList = MyJsonParser.getCityId(city.getCityName());
        System.out.println(cityList);
        model.addAttribute("cityObj",city);
        model.addAttribute("cityList",cityList);
        model.addAttribute("chosenCity", new City());
        return "choose-city";
    }

}
