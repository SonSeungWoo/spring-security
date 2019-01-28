package com.github.ssw.springsecurity.controller;

import com.github.ssw.springsecurity.domain.User;
import com.github.ssw.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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


    @GetMapping("/front")
    public String login(@ModelAttribute User user, HttpServletRequest request){
        UserDetails ckUserDetails = userService.loadUserByUsername(user.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(ckUserDetails.getUsername(), ckUserDetails.getPassword(), ckUserDetails.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        return "redirect:home";
    }

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model){
        System.out.println(request.getUserPrincipal().getName());
        model.addAttribute("username", request.getUserPrincipal().getName());
        return "home";
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request){
        System.out.println(request.getSession());
        return "hello";
    }
}
