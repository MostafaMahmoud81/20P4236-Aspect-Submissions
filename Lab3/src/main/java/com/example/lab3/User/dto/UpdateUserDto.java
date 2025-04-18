package com.example.lab3.User.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;


public class UpdateUserDto {

    private String username;

    @Email(message = "Email should be valid")
    private String email;

    private String password;

    @Pattern(regexp = "^[A-Z0-9]{8,10}$", message = "User number must be 8-10 alphanumeric characters")
    private String phoneNumber;

    @Positive(message = "Age must be positive")
    private Integer age;

    @Pattern(regexp = "^[0-9]{14}$", message = "User number must be 8-10 alphanumeric characters")
    private String nationalId;

    // Default constructor
    public UpdateUserDto() {
    }

    // Constructor with fields
    public UpdateUserDto(String username, String email, String password, String phoneNumber, Integer age, String nationalId) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.nationalId = nationalId;
    }

    // Getters and Setters


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}
