package com.example.inotify.models;

public class ProfileModel {

    private String profile_id;
    private String date;
    private String name;
    private String age;
    private String gender;
    private String occupation;
    private String email;
    private String phone;

    public ProfileModel() {
    }

    public ProfileModel(String date, String name, String age, String gender, String occupation, String email, String phone) {
        this.date = date;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.email = email;
        this.phone = phone;
    }

    public ProfileModel(String profile_id, String date, String name, String age, String gender, String occupation, String email, String phone) {
        this.profile_id = profile_id;
        this.date = date;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.email = email;
        this.phone = phone;
    }

    public String getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
