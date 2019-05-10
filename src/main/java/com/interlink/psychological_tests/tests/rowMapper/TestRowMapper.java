package com.interlink.psychological_tests.tests.rowMapper;

import com.interlink.psychological_tests.tests.dto.Test;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TestRowMapper implements RowMapper<Test> {

    @Override
    public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
        Test test = new Test();
        test.setId(rs.getInt("id"));
        test.setQuestion(rs.getString("question"));
        test.setVeryNegative(rs.getInt("very_negative"));
        test.setNegative(rs.getInt("negative"));
        test.setNeutral(rs.getInt("neutral"));
        test.setPositive(rs.getInt("positive"));
        test.setVeryPositive(rs.getInt("very_positive"));
        return test;
    }
}