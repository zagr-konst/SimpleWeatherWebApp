package com.zagr.konst.weatherApp.controller.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ParseURL {
    ///////
    //final static String API_KEY="rmGRBdI6GueRyx9cfoFIAjWJuGldeA1Z";
    final static String API_KEY="lNXcfnnNPswOqJBzloPRaclYB6Hh1eA4";
    //final static String API_KEY="m5ZY7jVSCRRrWMruRfMdTbjikRFdTSxO";
    ///////


    static String getCityInfo(String city_id) {
        //int =323903;
        String result="";
        String url =String.format("http://dataservice.accuweather.com/"
                + "forecasts/v1/hourly/1hour/%s?apikey=%s&metric=true&details=true",city_id, API_KEY);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                result+=line;}
        }catch(Exception e){}
        System.out.println("ParseURL:\n"+result);
        return result;
    }

    static String findCity(String word) {
        String result="";

        String url =String.format("http://dataservice.accuweather.com/locations/v1/"
                + "cities/autocomplete?apikey=%s&q=%s", API_KEY,word);
        System.out.println(url);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                result+=line;}
        }catch(IOException e){
            System.out.println("Parse URL: server return 503");
        }
        //System.out.println("ParseURL:\n"+result);
        return result;
    }
}
