package com.example.lab3.User;

import com.example.lab3.Transaction.Transaction;
import com.example.lab3.Transaction.TransactionRepository;
import com.example.lab3.User.dto.CreateUserDto;
import com.example.lab3.User.dto.UpdateUserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    // Get all students
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get student by ID
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // Create a new student
    public User createUser(CreateUserDto userDto) {
        User user = new User(
                userDto.getEmail(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getPhoneNumber(),
                userDto.getAge(),
                userDto.getNationalId()
        );
        return userRepository.save(user);
    }

    // Update an existing student
    public User updateStudent(Integer id, UpdateUserDto userDto) {
        // Find the existing student or throw an exception if not found
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Copy only non-null properties from DTO to entity
        BeanUtils.copyProperties(userDto, existingUser, getNullPropertyNames(userDto));

        // Save and return the updated student
        return userRepository.save(existingUser);
    }

    // Delete a student
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        userRepository.delete(user);
    }

    // Find student by student number
    public User findStudentByStudentNumber(String nationalId) {
        return userRepository.findByNationalId(nationalId);
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public User trnsToUser(Integer userId, Integer transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.getUserTransactions().add(transaction); // Add user to event
        return userRepository.save(user);
    }
}
