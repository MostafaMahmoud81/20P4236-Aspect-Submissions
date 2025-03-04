package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class Controller {

    @GetMapping
    public String getProduct() {
        return "Product is retrieved (GET) ";
    }

    @PostMapping
    public String postProduct() {
        return "Product is saved (POST) ";
    }

    @PutMapping
    public String putProduct() {
        return "Product is updated (PUT) ";
    }

    @DeleteMapping
    public String deleteProduct() {
        return "Product is deleted (DELETE) ";
    }


}
