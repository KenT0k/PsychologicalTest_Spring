package com.interlink.psychological_tests.tests.service;

import com.interlink.psychological_tests.tests.dto.Test;
import com.interlink.psychological_tests.tests.dto.UserWithTest;
import com.interlink.psychological_tests.tests.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> getTest(String titleOfTest) {
        return testRepository.getTest(titleOfTest);
    }

    public void addTest(String titleOfTest) {
        testRepository.addTest(titleOfTest);
    }

    public Test getTestByTitle(String titleOfTest) {
        return testRepository.getTestByTitle(titleOfTest);
    }

    public void addContentToTests(Test test, String titleOfTest) {
        testRepository.addContentToTests(test, titleOfTest);
    }

    public void renameTitleOfTest(String titleOfTest, String newTitleOfTest) {
        testRepository.renameTitleOfTest(titleOfTest, newTitleOfTest);
    }

    public List<Test> getCreatedTests() {
        return testRepository.getCreatedTests();
    }

    public void removeTest(String titleOfTable) {
        testRepository.removeTest(titleOfTable);
    }

    public List<Test> getResultFromUser() {
        return testRepository.getResultFromUser();
    }

    public List<UserWithTest> getResultFromAllUsers() {
        return testRepository.getResultFromAllUsers();
    }

    public void deleteResultFromUser(Integer idResult) {
        testRepository.deleteResultFromUser(idResult);
    }

    public void saveResult(String titleOfTest, Integer resultForTest) {
        testRepository.saveResult(titleOfTest, resultForTest);
    }
}