package com.pratikgkd.spring_security.controller;

import com.pratikgkd.spring_security.entity.User;
import com.pratikgkd.spring_security.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public User register(@RequestBody User user) {
    return userService.register(user);
  }

  @PostMapping("/login")
  public String login(@RequestBody User user) {
    return userService.verify(user);
  }

}
