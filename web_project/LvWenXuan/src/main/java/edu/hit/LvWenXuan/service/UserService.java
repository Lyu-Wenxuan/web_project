package edu.hit.LvWenXuan.service;

import edu.hit.LvWenXuan.domain.User;

public interface UserService {
    boolean regist(User user);

    User login(User user);
}
