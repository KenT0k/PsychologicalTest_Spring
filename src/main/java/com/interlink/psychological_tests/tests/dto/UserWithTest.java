package com.interlink.psychological_tests.tests.dto;

public class UserWithTest {
    private Integer id;
    private String firstName;
    private String lastName;
    private String group;
    private String titleOfTest;
    private Integer result;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitleOfTest() {
        return titleOfTest;
    }

    public void setTitleOfTest(String titleOfTest) {
        this.titleOfTest = titleOfTest;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}