package com.practice.simpleboard.security;

import com.practice.simpleboard.service.CustomeUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.headers().frameOptions().sameOrigin();

        http.csrf().disable()
            .headers().frameOptions().disable(); //h2-console 화면을 사용하기 위해 해당 옵션들을 disable
        http
            .authorizeRequests()
            // 허용하는 url
            .antMatchers("/resources/**","/loginError","/registration").permitAll()
            // admin 폴더에 경우 admin 권한이 있는 사용자에게만 허용
//            .antMatchers("/admin/**").hasAuthority("ADMIN")
            // user 폴더에 경우 user 권한이 있는 사용자에게만 허용
//            .antMatchers("/user/**").hasAuthority("USER")
            .anyRequest().authenticated();

        http.formLogin()
//            .loginPage("/login")
            .successHandler(new CustomAuthenticationSuccess()) // 로그인 성공 핸들러
            .failureHandler(new CustomAuthenticationFailure()) // 로그인 실패 핸들러
            .permitAll();



        //로그아웃
        http.logout()
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .logoutSuccessUrl("/login");            //로그아웃 후 보내질 페이지

        //접근권한 없을시 페이지
//        http.exceptionHandling().accessDeniedHandler(customAccessDenineHandler);
    }

    // 권한 처리
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        auth.authenticationProvider(customAuthenticationProvider);
    }
}