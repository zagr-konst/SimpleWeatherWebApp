package com.zagr.konst.weatherApp.service;

import com.zagr.konst.weatherApp.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    List<User> getAll();

    User readById(long id);

    User update(User user);

    void delete(long id);





}
