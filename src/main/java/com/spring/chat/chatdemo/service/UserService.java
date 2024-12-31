package com.spring.chat.chatdemo.service;

import com.spring.chat.chatdemo.model.User;
import com.spring.chat.chatdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void save(User user) {
        user.setStatus("ONLINE");
        repository.save(user);
    }

    public void disconnect(User user) {
        User storedUser = repository.findById(user.getNickName()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus("OFFLINE");
            repository.save(storedUser);
        }
    }

    public List<User> findAllConnectedUsers() {
        return repository.findAllByStatus("ONLINE");
    }
}
