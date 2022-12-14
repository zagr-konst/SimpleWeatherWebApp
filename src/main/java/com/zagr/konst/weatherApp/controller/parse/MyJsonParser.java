package com.zagr.konst.weatherApp.controller.parse;




import com.zagr.konst.weatherApp.exception.NullAnswerJsonException;
import com.zagr.konst.weatherApp.model.City;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyJsonParser {

    static Logger logger = LoggerFactory.getLogger(MyJsonParser.class);


    public static HashMap<String,String> getCityWeather(String id) {

        HashMap<String,String> weather_value = new HashMap<>();;

        String json = ParseURL.getCityWeatherInfo(id);
        JSONParser parser = new JSONParser();
        try {
            JSONArray rootJson = (JSONArray) parser.parse(json);
            JSONObject jsobj=null;
            for(Object o:rootJson) {
                jsobj = (JSONObject) o;
            }
            //parse t°
            JSONObject t_json = (JSONObject) jsobj.get("Temperature");
            String t = String.valueOf(t_json.get("Value"));

            //parse feel t°
            JSONObject feel_t_json = (JSONObject) jsobj.get("RealFeelTemperature");
            String feelT =String.valueOf(feel_t_json.get("Value"));

            //parse wind
            JSONObject wind_json = (JSONObject) jsobj.get("Wind");
            wind_json=(JSONObject) wind_json.get("Speed");
            String wind = wind_json.get("Value")+" "+wind_json.get("Unit");

            //parse IconPhrase
            String iconPhrase = String.valueOf(jsobj.get("IconPhrase"));

            //parse WeatherIcon
            String weatherIcon = String.valueOf(jsobj.get("WeatherIcon"));

            weather_value.put("Temperature", t);
            weather_value.put("RealFeelTemperature",feelT);
            weather_value.put("Wind", wind);
            weather_value.put("IconPhrase", iconPhrase);
            weather_value.put("WeatherIcon", weatherIcon);
        }catch(Exception e) {
            logger.error("Accuweather return null");
            throw new NullAnswerJsonException("Accuweather return null");
        }
        logger.info("Parsed: "+weather_value);
        return weather_value;
    }

    //////////////////////////////////////////////////////////////////

    public static List<City> getCityId(String word){
        ArrayList<City> cityList = new ArrayList(10);

        String json = ParseURL.findCity(word);


        JSONParser parser = new JSONParser();
        try {
            JSONArray rootJson = (JSONArray) parser.parse(json);
            JSONObject jsobj=null;
            for(Object o:rootJson) {
                jsobj = (JSONObject) o;

                City city = new City();

                //parse City name
                city.setCityName(String.valueOf(jsobj.get("LocalizedName")));


                //parse Country name
                JSONObject country_json = (JSONObject) jsobj.get("Country");

                city.setCountry(String.valueOf(country_json.get("LocalizedName")));

                //parse city id
                city.setCityID(Integer.valueOf(
                        String.valueOf(jsobj.get("Key"))));


                //parse region
                JSONObject adminArea_json = (JSONObject) jsobj.get("AdministrativeArea");
                city.setRegion(String.valueOf(adminArea_json.get("LocalizedName")));

                cityList.add(city);
            }
        }catch (Exception e) {
            logger.error("Accuweather return null");
            throw new NullAnswerJsonException("Accuweather return null");
        }



        return cityList;

    }

    public static City getCityById(long id) {
        String json = ParseURL.getCityInfo(id + "");

        City city = new City();
        city.setCityID(id);

        JSONParser parser = new JSONParser();
        try {
            JSONObject jsobj = (JSONObject) parser.parse(json);

            String name = String.valueOf(jsobj.get("EnglishName"));
            city.setCityName(name);

            JSONObject country_json = (JSONObject) jsobj.get("Country");
            String country = String.valueOf(country_json.get("EnglishName"));
            city.setCountry(country);


            JSONObject region_json = (JSONObject) jsobj.get("AdministrativeArea");
            String region = String.valueOf(region_json.get("EnglishName"));
            city.setRegion(region);

        }catch (Exception e) {
            logger.error("Accuweather return null");
            throw new NullAnswerJsonException("Accuweather return null");
        }
        logger.info("Parsed: "+city);
        return city;
    }

}