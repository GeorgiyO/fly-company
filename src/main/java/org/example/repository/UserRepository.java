package org.example.repository;

import org.example.entity.Role;
import org.example.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    final JdbcTemplate jdbc;
    RowMapper<User> mapper = (rs, i) -> new User().setId(rs.getInt("id"))
                                                  .setLogin(rs.getString("login"))
                                                  .setPassword(rs.getString("password"))
                                                  .setRole(new Role().setType(rs.getString("role")));

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    User one(String sql, Object... args) {
        var res = jdbc.query(sql, mapper, args);
        return res.size() == 0 ? null : res.get(0);
    }

    public User get(int id) throws NotFoundException {
        var res = one("call user_get(?)", id);
        if (res == null) throw new NotFoundException("User with id " + id + " not found");
        return res;
    }

    public User find(String login) throws NotFoundException {
        var res = one("call user_find(?)", login);
        if (res == null) throw new NotFoundException("User with login " + login + " not found");
        return res;
    }

    public List<User> allCashiers() {
        return jdbc.query(
            "call cashiers_all()",
            mapper
        );
    }

    public User addCashier(User u) {
        return one("call cashiers_add(?, ?)", u.getLogin(), u.getPassword());
    }

    public void remove(int id) {
        jdbc.update("call users_remove(?)", id);
    }

}
