package com.interlink.psychological_tests.tests.rowMapper;

import com.interlink.psychological_tests.tests.dto.Test;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CreatedTestsRowMapper implements RowMapper<Test> {

    @Override
    public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
        Test test = new Test();
        test.setId(rs.getInt("id"));
        test.setTitleOfTest(rs.getString("title"));
        test.setDate(rs.getDate("date"));
        return test;
    }
}