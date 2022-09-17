package com.zagr.konst.weatherApp.controller.parse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ParseURL {
    ///////
    final static String API_KEY=ApiKey.API_KEY;
    ///////

    static Logger logger = LoggerFactory.getLogger(ParseURL.class);


    static String getCityWeatherInfo(String city_id) {
        String result="";
        String url = String.format("http://dataservice.accuweather.com/"
                + "forecasts/v1/hourly/1hour/%s?apikey=%s&metric=true&details=true",city_id, API_KEY);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new URL(url).openStream(), "UTF-8"))) {

            for (String line; (line = reader.readLine()) != null;) {
                result+=line;}
            }catch(Exception e){
                logger.error("Parse URL: server return 503");
        }
        logger.info("ParseURL:" + result);
        return result;
    }

    static String findCity(String word) {
        String result="";

        String url =String.format("http://dataservice.accuweather.com/locations/v1/"
                + "cities/autocomplete?apikey=%s&q=%s", API_KEY,word);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                result+=line;}
        }catch(IOException e){
            logger.error("Parse URL: server return 503");
        }
        logger.info("ParseURL:" + result);
        return result;
    }


    static String getCityInfo(String city_id) {
        //int =323903;
        String result = "";
        String url = String.format("http://dataservice.accuweather.com/locations/v1"
                + "/%s?apikey=%s", city_id, API_KEY);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("Parse URL: server return 503");
        }

        logger.info("ParseURL: " + result);
        return result;
    }

}
