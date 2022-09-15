package com.zagr.konst.weatherApp.service.impl;

import com.zagr.konst.weatherApp.controller.parse.MyJsonParser;
import com.zagr.konst.weatherApp.model.City;
import com.zagr.konst.weatherApp.model.User;
import com.zagr.konst.weatherApp.repository.CityRepository;
import com.zagr.konst.weatherApp.repository.UserRepository;
import com.zagr.konst.weatherApp.service.CityService;
import com.zagr.konst.weatherApp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    CityRepository cityRepository;

    Logger logger = LoggerFactory.getLogger(MyJsonParser.class);

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public City create(City city) {
        if (city!=null) {
            logger.info("Saved city:" + city + " into db");
            return cityRepository.save(city);
        }else{
            logger.error("Can't save null city into db");
            throw new NullPointerException();
        }
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public City readById(long id) {
        return cityRepository.findById(id).orElseThrow(
                () -> new NullPointerException()
        );
    }

    @Override
    public City update(City city) {
        if (cityRepository.findById(city.getCityID())!=null) {
            logger.info("Successfully updated the city with id:" + city.getCityID());
            return create(city);
        }else{
            logger.error("Unable to update, cause city is null ");
            throw new NullPointerException();
        }
    }


    @Override
    public void delete(long id) {
        cityRepository.delete(readById(id));
    }
}
