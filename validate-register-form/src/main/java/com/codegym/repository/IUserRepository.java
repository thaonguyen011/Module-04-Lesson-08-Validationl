package com.codegym.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.codegym.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {
}
