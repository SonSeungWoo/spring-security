package com.github.ssw.springsecurity.repository;

import com.github.ssw.springsecurity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Leo.
 * User: ssw
 * Date: 2019-01-28
 * Time: 14:38
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from user where username = ?1",
            nativeQuery = true)
    User findByName(String name);

}
