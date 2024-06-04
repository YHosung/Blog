package com.example.Blog.controller;

import com.example.Blog.entity.User;
import com.example.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user) {
        userService.saveUser(user);
        return "signup_success";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userService.findByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            model.addAttribute("user", user);
            return "blog"; // 블로그 메인 페이지로 이동
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        // 로그아웃 처리 로직 추가
        return "redirect:/user/login";
    }
}
