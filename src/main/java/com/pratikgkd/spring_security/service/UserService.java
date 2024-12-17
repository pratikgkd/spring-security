package com.pratikgkd.spring_security.service;

import com.pratikgkd.spring_security.entity.User;
import com.pratikgkd.spring_security.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  private final AuthenticationManager authenticationManager;

  private final JwtService jwtService;

  public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
      AuthenticationManager authenticationManager, JwtService jwtService) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
  }


  public User register(User user) {
    user.setPassword(bCryptPasswordEncoder
        .encode(user.getPassword()));
    return userRepository.save(user);
  }

  public String verify(User user) {
    Authentication authenticate = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            user.getUserName(), user.getPassword()
        )
    );

    if (authenticate.isAuthenticated()) {
      return jwtService.generateToken(user);
    }

    return "failure";
  }
}

