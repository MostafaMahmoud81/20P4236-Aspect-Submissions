package com.example.lab3.User;

import com.example.lab3.User.dto.CreateUserDto;
import com.example.lab3.User.dto.UpdateUserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllStudents() {
        List<User> students = userService.getAllUsers();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> studentOptional = userService.getUserById(id);

        if (studentOptional.isPresent()) {
            User user = studentOptional.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserDto userDto) {
        User newStudent = userService.createUser(userDto);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @Valid @RequestBody UpdateUserDto updateUserDto) {
        try {
            User updatedStudent = userService.updateStudent(id, updateUserDto);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/trns")
    public ResponseEntity<User> trnsToUser(
            @PathVariable Integer userId,
            @RequestParam Integer transactionId) {
        User updated = userService.trnsToUser(userId, transactionId);
        return ResponseEntity.ok(updated);
    }


    // Find student by student number
    @GetMapping("/number/{userNumber}")
    public ResponseEntity<User> getStudentByStudentNumber(@PathVariable String userNumber) {
        User user = userService.findStudentByStudentNumber(userNumber);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
