package com.interlink.psychological_tests.tests.repository;

import com.interlink.psychological_tests.authentication.user.User;
import com.interlink.psychological_tests.tests.dto.Test;
import com.interlink.psychological_tests.tests.dto.UserWithTest;
import com.interlink.psychological_tests.tests.rowMapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TestRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;
    private final TestRowMapper testRowMapper;
    private final UserResultRowMapper userResultRowMapper;
    private final AllUsersResultRowMapper allUsersResultRowMapper;
    private final CreatedTestsRowMapper createdTestsRowMapper;

    @Autowired
    public TestRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                          JdbcTemplate jdbcTemplate,
                          TestRowMapper testRowMapper,
                          UserResultRowMapper userResultRowMapper,
                          AllUsersResultRowMapper allUsersResultRowMapper,
                          CreatedTestsRowMapper createdTestsRowMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
        this.testRowMapper = testRowMapper;
        this.userResultRowMapper = userResultRowMapper;
        this.allUsersResultRowMapper = allUsersResultRowMapper;
        this.createdTestsRowMapper = createdTestsRowMapper;
    }

    public List<Test> getTest(String titleOfTest) {
        String sql = "SELECT * FROM " + titleOfTest + "";
        List<Test> tests = namedParameterJdbcTemplate.query(sql, testRowMapper);
        tests = tests.stream()
                .sorted(Comparator.comparing(Test::getId))
                .collect(Collectors.toList());
        return tests;
    }

    public void addTest(String titleOfTest) {
        String sqlCreateThisTest = "CREATE TABLE " + titleOfTest + "( " +
                "id SERIAL PRIMARY KEY, " +
                "question VARCHAR(100) NOT NULL, " +
                "very_negative INTEGER NOT NULL, " +
                "negative INTEGER NOT NULL, " +
                "neutral INTEGER NOT NULL, " +
                "positive INTEGER NOT NULL, " +
                "very_positive INTEGER NOT NULL );";
        jdbcTemplate.execute(sqlCreateThisTest);
        String sqlAddTestToAddedTests = "INSERT INTO added_tests(title, date) VALUES(:title, :date)";
        SqlParameterSource parameterAddTask = new MapSqlParameterSource()
                .addValue("title", titleOfTest)
                .addValue("date", LocalDate.now());
        namedParameterJdbcTemplate.update(sqlAddTestToAddedTests, parameterAddTask);
    }

    public Test getTestByTitle(String titleOfTest) {
        try {
            String sql = "SELECT * FROM added_tests WHERE title = :title";
            return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("title", titleOfTest), createdTestsRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void addContentToTests(Test test, String titleOfTest) {
        String addContentToTests = "INSERT INTO " + titleOfTest + "(question, very_negative, negative, neutral, positive, very_positive) " +
                "VALUES(:question, :very_negative, :negative, :neutral, :positive, :very_positive)";
        SqlParameterSource parameterAddContentToTests = new MapSqlParameterSource()
                .addValue("question", test.getQuestion())
                .addValue("very_negative", test.getVeryNegative())
                .addValue("negative", test.getNegative())
                .addValue("neutral", test.getNeutral())
                .addValue("positive", test.getPositive())
                .addValue("very_positive", test.getVeryPositive());
        namedParameterJdbcTemplate.update(addContentToTests, parameterAddContentToTests);
    }

    public void renameTitleOfTest(String titleOfTest, String newTitleOfTest) {
        String sqlRenameTitleOfTest = "ALTER TABLE " + titleOfTest + " RENAME TO " + newTitleOfTest + "";
        namedParameterJdbcTemplate.update(sqlRenameTitleOfTest, new MapSqlParameterSource());
        String sqlRenameTitleOfTestInAddedTests = "UPDATE added_tests SET title = :" + titleOfTest +
                " WHERE title = :title";
        SqlParameterSource parameterRenameTitleOfTestInAddedTests = new MapSqlParameterSource()
                .addValue(titleOfTest, newTitleOfTest)
                .addValue("title", titleOfTest);
        namedParameterJdbcTemplate.update(sqlRenameTitleOfTestInAddedTests, parameterRenameTitleOfTestInAddedTests);
    }

    public List<Test> getCreatedTests() {
        String sql = "SELECT * FROM added_tests";
        List<Test> tests = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(), createdTestsRowMapper);
        tests = tests.stream()
                .sorted(Comparator.comparing(Test::getId))
                .collect(Collectors.toList());
        return tests;
    }

    public void removeTest(String titleOfTable) {
        String sqlDeleteTest = "DROP TABLE " + titleOfTable;
        jdbcTemplate.execute(sqlDeleteTest);
        String sqlDeleteTestFromAddedTests = "DELETE FROM added_tests WHERE title = :title";
        namedParameterJdbcTemplate.update(sqlDeleteTestFromAddedTests, new MapSqlParameterSource("title", titleOfTable));
    }

    public List<Test> getResultFromUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String sql = "SELECT r.* FROM users AS u INNER JOIN result AS r ON u.id = r.id_user WHERE u.id = :id";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("id", user.getId()), userResultRowMapper);
    }

    public List<UserWithTest> getResultFromAllUsers() {
        String sql = "SELECT * FROM users AS u INNER JOIN result AS r ON u.id = r.id_user";
        return namedParameterJdbcTemplate.query(sql, allUsersResultRowMapper);
    }

    public void deleteResultFromUser(Integer idResult) {
        String sqlDeleteResult = "DELETE FROM result AS r USING users AS u WHERE r.id_user = u.id AND r.id = :id";
        namedParameterJdbcTemplate.update(sqlDeleteResult, new MapSqlParameterSource("id", idResult));
    }

    public void saveResult(String titleOfTest, Integer resultForTest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String sqlInsertToResult = "INSERT INTO result (id_user, title, date, result) VALUES(:id_user, :title, :date, :result)";
        SqlParameterSource parameterSqlInsertToResult = new MapSqlParameterSource()
                .addValue("id_user", user.getId())
                .addValue("title", titleOfTest)
                .addValue("date", LocalDate.now())
                .addValue("result", resultForTest);
        namedParameterJdbcTemplate.update(sqlInsertToResult, parameterSqlInsertToResult);
    }
}