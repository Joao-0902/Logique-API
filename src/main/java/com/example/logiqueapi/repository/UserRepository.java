package com.example.logiqueapi.repository;

import java.util.List;

import com.example.logiqueapi.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    public List<UserModel> findByName(String name);
}