package com.pratikgkd.spring_security.repository;

import com.pratikgkd.spring_security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User findByUserName(String userName);
}
