package com.example.lab3.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    // Find student by student number
    User findByNationalId(String nationalId);
}
