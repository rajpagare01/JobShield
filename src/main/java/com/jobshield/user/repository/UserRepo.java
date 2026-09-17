package com.jobshield.user.repository;

import com.jobshield.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail( String email);
    boolean existsByEmail( String email);


}
