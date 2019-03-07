package com.example.inotify.models;

public class ProfileModel {

    private String PROFILE_ID;
    private String DATE;
    private String NAME;
    private String AGE;
    private String GENDER;
    private String OCCUPATION;
    private String EMAIL;
    private String PHONE;

    public ProfileModel() {
    }

    public ProfileModel(String DATE, String NAME, String AGE, String GENDER, String OCCUPATION, String EMAIL, String PHONE) {
        this.DATE = DATE;
        this.NAME = NAME;
        this.AGE = AGE;
        this.GENDER = GENDER;
        this.OCCUPATION = OCCUPATION;
        this.EMAIL = EMAIL;
        this.PHONE = PHONE;
    }

    public ProfileModel(String PROFILE_ID, String DATE, String NAME, String AGE, String GENDER, String OCCUPATION, String EMAIL, String PHONE) {
        this.PROFILE_ID = PROFILE_ID;
        this.DATE = DATE;
        this.NAME = NAME;
        this.AGE = AGE;
        this.GENDER = GENDER;
        this.OCCUPATION = OCCUPATION;
        this.EMAIL = EMAIL;
        this.PHONE = PHONE;
    }

    public String getPROFILE_ID() {
        return PROFILE_ID;
    }

    public void setPROFILE_ID(String PROFILE_ID) {
        this.PROFILE_ID = PROFILE_ID;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getAGE() {
        return AGE;
    }

    public void setAGE(String AGE) {
        this.AGE = AGE;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getOCCUPATION() {
        return OCCUPATION;
    }

    public void setOCCUPATION(String OCCUPATION) {
        this.OCCUPATION = OCCUPATION;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }
}
