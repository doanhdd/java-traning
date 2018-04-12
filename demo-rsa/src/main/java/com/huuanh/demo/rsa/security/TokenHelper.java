package com.huuanh.demo.rsa.security;

import com.huuanh.demo.rsa.common.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenHelper {

  public static void addAuthentication(HttpServletResponse response, String terminalUuid) {
    String jwt = Jwts.builder()
        .setSubject(terminalUuid)
        .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
        .compact();

    response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + " " + jwt);
  }

  public static String getToken(HttpServletRequest request) {
    String header = request.getHeader(SecurityConstants.HEADER_STRING);

    if (header != null) {
      return Jwts.parser()
          .setSigningKey(SecurityConstants.SECRET)
          .parseClaimsJws(header.replace(SecurityConstants.TOKEN_PREFIX, ""))
          .getBody()
          .getSubject();
    }

    return null;
  }
}