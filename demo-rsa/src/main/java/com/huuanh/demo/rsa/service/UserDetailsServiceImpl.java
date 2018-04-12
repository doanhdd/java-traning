package com.huuanh.demo.rsa.service;

import static com.huuanh.demo.rsa.common.SecurityConstants.ROLE_ADMIN;
import static com.huuanh.demo.rsa.common.SecurityConstants.ROLE_ADMIN_TEXT;
import static com.huuanh.demo.rsa.common.SecurityConstants.ROLE_USER_TEXT;

import com.huuanh.demo.rsa.model.CustomUserDetail;
import com.huuanh.demo.rsa.model.User;
import com.huuanh.demo.rsa.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email);

    if (user == null) {
      throw new UsernameNotFoundException("Bad credentials");
    }
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    grantedAuthorities.add(new SimpleGrantedAuthority(
        user.getRole().equals(ROLE_ADMIN) ? ROLE_ADMIN_TEXT : ROLE_USER_TEXT));

    return new CustomUserDetail(user, user.getEmail(), user.getPassword(), grantedAuthorities);
  }
}