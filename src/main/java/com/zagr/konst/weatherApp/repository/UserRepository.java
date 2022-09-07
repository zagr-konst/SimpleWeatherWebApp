package com.zagr.konst.weatherApp.repository;

import com.zagr.konst.weatherApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
