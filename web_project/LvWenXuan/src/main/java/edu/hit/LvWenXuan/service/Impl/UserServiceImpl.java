package edu.hit.LvWenXuan.service.Impl;

import edu.hit.LvWenXuan.dao.UserDao;
import edu.hit.LvWenXuan.dao.Impl.UserDaoImpl;
import edu.hit.LvWenXuan.domain.User;
import edu.hit.LvWenXuan.service.UserService;


public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean regist(User user)
    {
        User u = userDao.findByUsername(user.getUsername());

        //判断u是否为null
        if (u != null) {
            //用户名存在，注册失败
            return false;
        }

        userDao.save(user);
        return true;
    }

    @Override
    public User login(User user)
    {
        User u = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return u;
    }


}