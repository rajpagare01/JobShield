package com.jobshield.user.repository;

import com.jobshield.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail( String email);
    boolean existsByEmail( String email);


}
