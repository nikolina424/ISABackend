package com.example.isabackend.repository;

import com.example.isabackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findOneById(Long id);

    User findOneByUsername(String username);



}
