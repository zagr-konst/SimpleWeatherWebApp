package com.zagr.konst.weatherApp.controller;

import com.zagr.konst.weatherApp.controller.parse.MyJsonParser;
import com.zagr.konst.weatherApp.model.City;
import com.zagr.konst.weatherApp.model.User;
import com.zagr.konst.weatherApp.security.SecurityUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class HomeController {

    @GetMapping({"/","/home"})
    public String home(Model model){
        System.out.println("####");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();


        User user = userDetails.getAuthUser();
        City city = user.getCityID();
        HashMap<String,String> weatherValue =  MyJsonParser.getCityWeather(city.getCityID()+"");

        model.addAttribute("city",city);
        model.addAttribute("image", getIcon(weatherValue.get("WeatherIcon")));
        model.addAttribute("cityWeather",weatherValue);
        return "home";
    }



    private String getIcon(String weatherIcon){
        String weather = "cloud";
        switch (Integer.parseInt(weatherIcon)) {
            case 1,2,3,4, 30, 31:{
                System.out.println("sun");
                weather = "sun";
                break;
            }case 5,6, 20,21:{
                weather = "sun&cloud";
                break;
            }case 7,8,11, 19, 32:{
                weather = "cloud";
                break;
            }case 12,13,14, 18:{
                weather = "rain";
                break;
            }case 15,16,17:{
                weather = "storm";
                break;
            }case 22,23,24,25,26,29:{
                weather = "snow";
                break;
            }case 33,34,35:{
                weather = "moon";
                break;
            }case 36,37,38,43,44:{
                weather = "moon&cloud";
                break;
            }case 39,40,41,42:{
                weather = "moon&rain";
                break;
            }

        }
        return weather;
    }

}
