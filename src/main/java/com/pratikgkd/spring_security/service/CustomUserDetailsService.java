package com.pratikgkd.spring_security.service;

import com.pratikgkd.spring_security.CustomUserDetails;
import com.pratikgkd.spring_security.entity.User;
import com.pratikgkd.spring_security.repository.UserRepository;
import java.util.Objects;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUserName(username);
    if (Objects.isNull(user)) {
      System.out.println("User not available");
      throw new UsernameNotFoundException("User not found");
    }
    return new CustomUserDetails(user);
  }
}