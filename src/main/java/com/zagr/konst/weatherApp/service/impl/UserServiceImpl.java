package com.zagr.konst.weatherApp.service.impl;

import com.zagr.konst.weatherApp.model.User;
import com.zagr.konst.weatherApp.repository.UserRepository;
import com.zagr.konst.weatherApp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if (user!=null)
            return userRepository.save(user);
        else throw new NullPointerException();
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
        if (userRepository.findById(user.getId())!=null)
            return create(user);
        else throw new NullPointerException();
    }

    @Override
    public void delete(long id) {
        userRepository.delete(readById(id));
    }
}
