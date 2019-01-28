package com.github.ssw.springsecurity.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Leo.
 * User: ssw
 * Date: 2019-01-28
 * Time: 14:30
 */
@Data
@Entity
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
