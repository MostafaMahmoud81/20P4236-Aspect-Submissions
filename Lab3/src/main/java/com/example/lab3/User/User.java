package com.example.lab3.User;
import com.example.lab3.Transaction.Transaction;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private Integer age;
    private String nationalId;

    @OneToMany(mappedBy = "sender")
    private List<Transaction> userTransactions;



    public User() {
    }

    public User(String email, String username, String password, String phoneNumber, Integer age, String nationalId) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.nationalId = nationalId;
    }

    public Integer getAge() {
        return age;
    }

    public String getNationalId() {
        return nationalId;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<Transaction> getUserTransactions() {
        return userTransactions;
    }

    public void setUserTransactions(List<Transaction> userTransactions) {
        this.userTransactions = userTransactions;
    }
}

