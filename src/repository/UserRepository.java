package repository;

import entity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(long id);
    List<User> findByName(String name);
}
