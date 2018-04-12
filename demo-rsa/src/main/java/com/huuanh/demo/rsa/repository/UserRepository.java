package com.huuanh.demo.rsa.repository;

import com.huuanh.demo.rsa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

  User findByEmail(String email);

  User findByEmailAndPassword(String email, String password);

}
