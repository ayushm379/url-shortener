package io.at.tinyurl.accountservice.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.at.tinyurl.accountservice.model.Account;
import io.at.tinyurl.accountservice.repository.AccountRepository;
import io.at.tinyurl.accountservice.service.impl.JwtTokenService;

@Slf4j
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtUtils;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
      try {
        String jwt = parseJwt(request);
        if (jwt != null && jwtUtils.validate(jwt)) {
            String username = jwtUtils.getUsername(jwt);
  
            Account account = accountRepository.getAccountByUsernameAndIsDeleted(username, false);
            if(account == null) {
                throw new RuntimeException("Account not found");
            }

            List<GrantedAuthority> authorities = account.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getCode()))
                .collect(Collectors.toList());
          
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                new User(username, null, authorities),
                null,
                authorities
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      } catch (Exception e) {
        log.error("Cannot set user authentication: {}", e);
      }
  
      filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}
