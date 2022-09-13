package com.zagr.konst.weatherApp.service.impl;

import com.zagr.konst.weatherApp.model.City;
import com.zagr.konst.weatherApp.model.User;
import com.zagr.konst.weatherApp.repository.CityRepository;
import com.zagr.konst.weatherApp.repository.UserRepository;
import com.zagr.konst.weatherApp.service.CityService;
import com.zagr.konst.weatherApp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public City create(City city) {
        if (city!=null)
            return cityRepository.save(city);
        else throw new NullPointerException();
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
        if (cityRepository.findById(city.getCityID())!=null)
            return create(city);
        else throw new NullPointerException();
    }


    @Override
    public void delete(long id) {
        cityRepository.delete(readById(id));
    }
}
