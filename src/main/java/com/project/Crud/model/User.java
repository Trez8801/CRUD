package com.project.Crud.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * User object that will be used as a model
 * with the fields id, name, email, bio, etc...
 * to be filled by the user input from the front-end
 */
public class User {
    @NotNull(message = "Id is mandatory")
    private Integer id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Email(message = "Email is mandatory")
    private String email;
    private String bio;
    private String yearsOfExperience;
    private List<String> programmingLanguages;
    private List<String> frameworks;
    private List<String> team;
    private List<String> pay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(List<String> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public List<String> getFrameworks() {
        return frameworks;
    }

    public void setFrameworks(List<String> frameworks) {
        this.frameworks = frameworks;
    }

    public List<String> getTeam() {
        return team;
    }

    public void setTeam(List<String> team) {
        this.team = team;
    }

    public List<String> getPay() {
        return pay;
    }

    public void setPay(List<String> pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                ", yearsOfExperience='" + yearsOfExperience + '\'' +
                ", programmingLanguages=" + programmingLanguages +
                ", frameworks=" + frameworks +
                ", team=" + team +
                ", pay=" + pay +
                '}';
    }
}
