package com.zagr.konst.weatherApp.controller;

import com.zagr.konst.weatherApp.controller.parse.MyJsonParser;
import com.zagr.konst.weatherApp.model.City;
import com.zagr.konst.weatherApp.model.User;
import com.zagr.konst.weatherApp.security.SecurityUserDetails;
import com.zagr.konst.weatherApp.service.CityService;
import com.zagr.konst.weatherApp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    UserService userService;
    CityService cityService;

    PasswordEncoder passwordEncoder;

    public UserController(UserService userService,
                          CityService cityService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.cityService = cityService;
        this.passwordEncoder = passwordEncoder;
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

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.create(user);
        model.addAttribute("cityObj", new City());
        model.addAttribute("chosenCity",new City());
        return "redirect:/login";
    }

    @GetMapping("/selectCity")
    public String select(Model model) {


      //  ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("cityObj", new City());
        model.addAttribute("chosenCity", new City());
//        modelAndView.setViewName("choose-city");
        return "choose-city";
    }

    @PostMapping("/selectCity")
    public String select(@ModelAttribute("chosenCity") City city) {

        city = MyJsonParser.getCityById(city.getCityID());
        cityService.create(city);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();

        User user = userDetails.getAuthUser();
        user.setCityID(city);
        userService.update(user);

        return "redirect:/home";
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


    @GetMapping("/validation")
    public String checkIfUserChoseCity(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
        User user = userDetails.getAuthUser();

        if (user.getCityID()!=null) return "redirect:/home";
        else return "redirect:/selectCity";
    }


}
