package com.zagr.konst.weatherApp.service;

import com.zagr.konst.weatherApp.model.City;
import com.zagr.konst.weatherApp.model.User;

import java.util.List;

public interface CityService {

    City create(City city);

    List<City> getAll();

    City readById(long id);

    City update(City city);

    void delete(long id);





}
