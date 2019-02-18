package com.github.ssw.springsecurity.domain;

import lombok.Data;

import java.util.Collection;

/**
 * Created by Leo.
 * User: ssw
 * Date: 2019-02-18
 * Time: 10:04
 */
@Data
public class AuthenticationToken {

    private String username;
    private Collection authorities;
    private String token;

    public AuthenticationToken() {

    }

    public AuthenticationToken(String username, Collection authorities, String token) {
        this.username = username;
        this.authorities = authorities;
        this.token = token;
    }
}
