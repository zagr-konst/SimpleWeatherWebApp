package com.zagr.konst.weatherApp.service.impl;

import com.zagr.konst.weatherApp.controller.parse.MyJsonParser;
import com.zagr.konst.weatherApp.model.User;
import com.zagr.konst.weatherApp.repository.UserRepository;
import com.zagr.konst.weatherApp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(MyJsonParser.class);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if (user!=null) {
            logger.info("Saved user: " + user + " into db");
            return userRepository.save(user);
        }else{
            logger.error("Can't save null user into db");
            throw new NullPointerException();
        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User readById(long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NullPointerException()
        );
    }

    @Override
    public User update(User user) {
        if (userRepository.findById(user.getId())!=null) {
            logger.info("Successfully updated the user with id:" + user.getId());
            return create(user);
        }else{
            throw new NullPointerException();
        }
    }

    @Override
    public void delete(long id) {
        userRepository.delete(readById(id));
    }
}
