package com.interlink.psychological_tests.authentication.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class UserRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    @Autowired
    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    void saveUser(User user) {
        String sqlInsertToUser = "INSERT INTO users(username, password, role, first_name, last_name, email, study_group)" +
                "VALUES(:username, :password, :role, :first_name, :last_name, :email, :study_group)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String role;
        if (checkOnEmptyTableUsers() != 0) {
            role = "USER";
        } else {
            role = "ADMIN";
        }
        SqlParameterSource parameterInsertToUsers = new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("password", user.getPassword())
                .addValue("role", role)
                .addValue("first_name", user.getFirstName())
                .addValue("last_name", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("study_group", user.getGroup());
        jdbcTemplate.update(sqlInsertToUser, parameterInsertToUsers, keyHolder, new String[]{"id"});
        user.setId((int) Objects.requireNonNull(keyHolder.getKey()).longValue());
        int idRole;
        if (checkOnEmptyTableUserRole() != 0) {
            idRole = 2;
        } else {
            idRole = 1;
        }
        String sqlInsertToRole = "INSERT INTO user_role(id_user, id_role) VALUES(:id_user, :id_role)";
        SqlParameterSource parameterInsertToUserRole = new MapSqlParameterSource()
                .addValue("id_user", user.getId())
                .addValue("id_role", idRole);
        jdbcTemplate.update(sqlInsertToRole, parameterInsertToUserRole);
    }

    void updateUser(User user, String username) {
        String sqlUpdateUsers = "UPDATE users SET password = :password, first_name = :first_name, last_name = :last_name, email = :email, study_group = :study_group WHERE username = :username";
        SqlParameterSource parameterUpdateUsers = new MapSqlParameterSource()
                .addValue("password", user.getPassword())
                .addValue("first_name", user.getFirstName())
                .addValue("last_name", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("study_group", user.getGroup())
                .addValue("username", username);
        jdbcTemplate.update(sqlUpdateUsers, parameterUpdateUsers);
    }

    User getUserByUsername(String username) {
        try {
            String sql = "SELECT * FROM users WHERE username = :username";
            return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("username", username), userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    void doUserIsAdmin(int id) {
        String sqlUpdateUserRole = "UPDATE user_role SET id_role = 1 WHERE id_user = :id";
        jdbcTemplate.update(sqlUpdateUserRole, new MapSqlParameterSource("id", id));
        String sqlUpdateUser = "UPDATE users SET role = 'ADMIN' WHERE id = :id";
        jdbcTemplate.update(sqlUpdateUser, new MapSqlParameterSource("id", id));
    }

    void doUserIsUser(int id) {
        String sqlUpdateUserRole = "UPDATE user_role SET id_role = 2 WHERE id_user = :id";
        jdbcTemplate.update(sqlUpdateUserRole, new MapSqlParameterSource("id", id));
        String sqlUpdateUser = "UPDATE users SET role = 'USER' WHERE id = :id";
        jdbcTemplate.update(sqlUpdateUser, new MapSqlParameterSource("id", id));
    }

    private Integer checkOnEmptyTableUsers() {
        String sql = "SELECT count(*) FROM users";
        return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
    }

    private Integer checkOnEmptyTableUserRole() {
        String sql = "SELECT count(*) FROM user_role";
        return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
    }
}