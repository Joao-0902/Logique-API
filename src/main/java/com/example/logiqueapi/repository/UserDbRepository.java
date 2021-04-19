package com.example.logiqueapi.repository;

import com.example.logiqueapi.model.User;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDbRepository extends PagingAndSortingRepository<User, Long> {
    User findByName(String name);
}
