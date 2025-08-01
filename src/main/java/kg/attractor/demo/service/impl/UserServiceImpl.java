package kg.attractor.demo.service.impl;

import kg.attractor.demo.dao.UserDao;
import kg.attractor.demo.dto.UserDto;
import kg.attractor.demo.exceptions.UserNotFoundException;
import kg.attractor.demo.model.User;
import kg.attractor.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final List<User> users;
    private final UserDao userDao;
    private final PasswordEncoder encoder;

    @Override
    public List<User> getUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> findByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public User createUser(UserDto user) {
//        int newUserId = userDao.addUser(user);
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        return newUser;
    }

    public User save(User user) {
        return user;
    }


}
