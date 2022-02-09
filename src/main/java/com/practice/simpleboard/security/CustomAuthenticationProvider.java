package com.practice.simpleboard.security;

import com.practice.simpleboard.service.CustomeUserDetailService;
import com.practice.simpleboard.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomeUserDetailService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserVo userVo = (UserVo) userService.loadUserByUsername(username);

        if(!passwordEncoder.matches(password, userVo.getPassword()) ) {
            throw new BadCredentialsException(username);
        }

        return new UsernamePasswordAuthenticationToken(userVo, password, Arrays.asList(new SimpleGrantedAuthority(userVo.getAuthorCode())));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
