package com.huuanh.demo.rsa.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetail extends User {

  private Integer userId;
  private String email;

  public CustomUserDetail(String username, String password,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
  }

  public CustomUserDetail(String username, String password, boolean enabled,
      boolean accountNonExpired,
      boolean credentialsNonExpired, boolean accountNonLocked,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
        authorities);
  }

  public CustomUserDetail(com.huuanh.demo.rsa.model.User user, String username, String password,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.userId = user.getUserId();
    this.email = user.getEmail();
  }

  public com.huuanh.demo.rsa.model.User asUser() {
    com.huuanh.demo.rsa.model.User user = new com.huuanh.demo.rsa.model.User();
    user.setUserId(userId);
    user.setEmail(email);
    return user;
  }

}
