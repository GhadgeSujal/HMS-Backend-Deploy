package com.Hospital.Management.System.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Hospital.Management.System.loginEntity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
