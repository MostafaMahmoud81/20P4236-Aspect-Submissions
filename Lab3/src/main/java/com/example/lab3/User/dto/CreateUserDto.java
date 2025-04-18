package com.example.lab3.User.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class CreateUserDto {
    @NotBlank(message = "Name is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Student number is required")
    @Pattern(regexp = "^[A-Z0-9]{8,10}$", message = "User number must be 8-10 alphanumeric characters")
    private String phoneNumber;

    @NotNull(message = "Age is required")
    @Positive(message = "Age must be positive")
    private Integer age;

    @NotNull(message = "National Id is required")
    @Pattern(regexp = "^[0-9]{14}$", message = "User number must be 8-10 alphanumeric characters")
    private String nationalId;

    // Default constructor
    public CreateUserDto() {
    }

    // Constructor with fields
    public CreateUserDto(String username, String email, String password, String phoneNumber, Integer age, String nationalId) {
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
