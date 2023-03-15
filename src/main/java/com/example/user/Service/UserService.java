package com.example.user.Service;

import com.example.user.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUser();

    public User createUser(User user);

    public Optional<User> getUserById(Long id);

    public void deleteUserById(Long id);

    public void updateUser(Long id, String name);
}
