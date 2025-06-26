package com.example.elasticsearchdemo.repository;

import com.example.elasticsearchdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}