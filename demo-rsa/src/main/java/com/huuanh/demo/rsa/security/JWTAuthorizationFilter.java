package com.huuanh.demo.rsa.security;

import static com.huuanh.demo.rsa.common.SecurityConstants.HEADER_STRING;
import static com.huuanh.demo.rsa.common.SecurityConstants.ROLE_ADMIN;
import static com.huuanh.demo.rsa.common.SecurityConstants.ROLE_ADMIN_TEXT;
import static com.huuanh.demo.rsa.common.SecurityConstants.ROLE_USER_TEXT;
import static com.huuanh.demo.rsa.common.SecurityConstants.SECRET;
import static com.huuanh.demo.rsa.common.SecurityConstants.TOKEN_PREFIX;

import com.huuanh.demo.rsa.model.User;
import com.huuanh.demo.rsa.repository.UserRepository;
import com.huuanh.demo.rsa.service.UserService;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  private UserService userService;

  public JWTAuthorizationFilter(AuthenticationManager authManager, UserService userService) {
    super(authManager);
    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req,
      HttpServletResponse res,
      FilterChain chain) throws IOException, ServletException {
    String header = req.getHeader(HEADER_STRING);
    if (header == null || !header.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(req, res);
      return;
    }
    UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(req, res);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);
    if (token != null) {
      // parse the token.
      String email = Jwts.parser()
          .setSigningKey(SECRET)
          .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
          .getBody()
          .getSubject();
      if (email != null) {
        User user = userService.findByEmail(email);

        if (user == null) {
          throw new UsernameNotFoundException("Bad credentials");
        }
        user.setPassword(null);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(
            user.getRole().equals(ROLE_ADMIN) ? ROLE_ADMIN_TEXT : ROLE_USER_TEXT));

        return new UsernamePasswordAuthenticationToken(email, user, grantedAuthorities);
      }
      return null;
    }
    return null;
  }
}