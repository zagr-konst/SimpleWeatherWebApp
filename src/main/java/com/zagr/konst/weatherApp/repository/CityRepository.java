package com.zagr.konst.weatherApp.repository;

import com.zagr.konst.weatherApp.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
