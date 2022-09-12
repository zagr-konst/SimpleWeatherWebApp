package com.zagr.konst.weatherApp.controller;

import com.zagr.konst.weatherApp.controller.parse.MyJsonParser;
import com.zagr.konst.weatherApp.model.City;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class HomeController {

    @GetMapping({"/","/home"})
    public String home(Model model){

        City city = (City) model.getAttribute("city");
        if (city!=null) {
            model.addAttribute("cityWeather",
                    MyJsonParser.getCityWeather(city.getCityID()+"")
                    );
        }
        return "home";
    }

}
