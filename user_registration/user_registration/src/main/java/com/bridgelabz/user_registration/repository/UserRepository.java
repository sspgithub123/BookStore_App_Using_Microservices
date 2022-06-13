package com.bridgelabz.user_registration.repository;


import com.bridgelabz.user_registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * from user WHERE email= :email",nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * from user WHERE email= :email",nativeQuery = true)
    User findByMail(String email);

}

