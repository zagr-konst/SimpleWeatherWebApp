package com.zagr.konst.weatherApp.exception;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public void canntFindHandler(Exception ex){
        ex.printStackTrace();
    }

    @ExceptionHandler(PSQLException.class)
    public String sqlError(Exception ex){
        System.out.println(ex.getMessage());
        return "redirect:/users/register?error";
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(NullAnswerJsonException.class)
    public ModelAndView apiError(Exception ex){
        ModelAndView model = new ModelAndView("error");
        model.addObject("errorCode","502 Bad Gateway");
        model.addObject("errorMessage",ex.getMessage()
                +", because the count of requests of the free accuweather token has expired for today");
        return model;
    }
}
