package com.finIntell.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finIntell.entity.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

	Optional<Users> findByEmail(String email);

}
