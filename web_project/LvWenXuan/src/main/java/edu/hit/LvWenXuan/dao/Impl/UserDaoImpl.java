package edu.hit.LvWenXuan.dao.Impl;

import edu.hit.LvWenXuan.domain.User;
import edu.hit.LvWenXuan.dao.UserDao;
import edu.hit.LvWenXuan.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
public class UserDaoImpl implements UserDao{

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findByUsername(String username)
    {
        User user = null;
        try {
            // 1. 定义sql
            String sql = "SELECT * FROM tab_user WHERE username = ?";
            // 2. 执行Sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {
        }

        return user;
    }

    @Override
    public void save(User user)
    {
        String sql = "INSERT INTO tab_user(username, password, name, birthday, sex, telephone, email) " +
                " VALUES (?,?,?,?,?,?,?) ";

        template.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail());
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {

            String sql = "SELECT * FROM tab_user WHERE username = ? AND password = ?";

            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (Exception e) {
        }

        return user;
    }

}
