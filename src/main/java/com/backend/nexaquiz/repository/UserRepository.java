package com.backend.nexaquiz.repository;

import java.util.List;
import java.util.Optional;

import com.backend.nexaquiz.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.nexaquiz.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    long countByRole(Role role);

    List<User> findByRoleOrderByFirstName(Role role);

    List<User> findByRole(Role role);
}