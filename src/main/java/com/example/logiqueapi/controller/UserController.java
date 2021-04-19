package com.example.logiqueapi.controller;

import java.util.List;
import java.util.Optional;

import com.example.logiqueapi.model.UserModel;
import com.example.logiqueapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userR;

    @PostMapping("/create")
    public UserModel userModel(@RequestBody UserModel userModel) {
        return this.userR.save(userModel);
    }

    @GetMapping("/{name}")
    public List<UserModel> list(@PathVariable("name") String name){
        return this.userR.findByName(name);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Optional<UserModel>> findById(@PathVariable long id) {
        Optional<UserModel> userModel = userR.findById(id);
        if (userModel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userModel);
    }
}