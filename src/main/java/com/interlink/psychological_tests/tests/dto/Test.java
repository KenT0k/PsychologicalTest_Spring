package com.interlink.psychological_tests.tests.dto;

import java.util.Date;

public class Test {
    private Integer id;
    private Integer idUser;
    private String titleOfTest;
    private String question;
    private Integer veryNegative;
    private Integer negative;
    private Integer neutral;
    private Integer positive;
    private Integer veryPositive;
    private Integer result;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getTitleOfTest() {
        return titleOfTest;
    }

    public void setTitleOfTest(String titleOfTest) {
        this.titleOfTest = titleOfTest;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getVeryNegative() {
        return veryNegative;
    }

    public void setVeryNegative(Integer veryNegative) {
        this.veryNegative = veryNegative;
    }

    public Integer getNegative() {
        return negative;
    }

    public void setNegative(Integer negative) {
        this.negative = negative;
    }

    public Integer getNeutral() {
        return neutral;
    }

    public void setNeutral(Integer neutral) {
        this.neutral = neutral;
    }

    public Integer getPositive() {
        return positive;
    }

    public void setPositive(Integer positive) {
        this.positive = positive;
    }

    public Integer getVeryPositive() {
        return veryPositive;
    }

    public void setVeryPositive(Integer veryPositive) {
        this.veryPositive = veryPositive;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}