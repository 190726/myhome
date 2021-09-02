package com.sk.myhome.repository;

import com.sk.myhome.model.Board;
import com.sk.myhome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
