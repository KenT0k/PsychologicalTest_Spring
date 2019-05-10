package com.interlink.psychological_tests.tests.rowMapper;

import com.interlink.psychological_tests.tests.dto.UserWithTest;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AllUsersResultRowMapper implements RowMapper<UserWithTest> {

    @Override
    public UserWithTest mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserWithTest userWithTest = new UserWithTest();
        userWithTest.setId(rs.getInt("id"));
        userWithTest.setFirstName(rs.getString("first_name"));
        userWithTest.setLastName(rs.getString("last_name"));
        userWithTest.setGroup(rs.getString("study_group"));
        userWithTest.setTitleOfTest(rs.getString("title"));
        userWithTest.setResult(rs.getInt("result"));
        return userWithTest;
    }
}