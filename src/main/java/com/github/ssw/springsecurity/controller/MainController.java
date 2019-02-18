package com.github.ssw.springsecurity.controller;

import com.github.ssw.springsecurity.domain.AuthenticationToken;
import com.github.ssw.springsecurity.domain.User;
import com.github.ssw.springsecurity.service.UserService;
import com.github.ssw.springsecurity.util.JwtTokenProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Leo.
 * User: ssw
 * Date: 2019-01-28
 * Time: 14:29
 */
@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    private final JwtTokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;


    @GetMapping("/token")
    @ResponseBody
    public ResponseEntity<String> login(@ModelAttribute User user, HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(jwt);

        /*UserDetails ckUserDetails = userService.loadUserByUsername(user.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(ckUserDetails.getUsername(), ckUserDetails.getPassword(), ckUserDetails.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        Date exDate = new Date(System.currentTimeMillis() + 60000);

        String jwtToken = Jwts.builder().setSubject(ckUserDetails.getUsername()).claim("ADMIN", user).setIssuedAt(new Date()).setExpiration(exDate)
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        return ResponseEntity.ok(new AuthenticationToken(ckUserDetails.getUsername(), ckUserDetails.getAuthorities(), jwtToken));*/
    }

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model) {
        System.out.println(request.getUserPrincipal().getName());
        model.addAttribute("username", request.getUserPrincipal().getName());
        return "home";
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        System.out.println(request.getSession());
        return "hello";
    }
}
