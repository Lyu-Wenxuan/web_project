package edu.hit.LvWenXuan.dao;

import edu.hit.LvWenXuan.domain.User;
public interface UserDao{
    User findByUsername(String username);

    void save(User user);

    User findByUsernameAndPassword(String username, String password);
}