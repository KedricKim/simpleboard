package com.practice.simpleboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;
    @Autowired
    CustomAuthenticationDenine customAuthenticationDenine;

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.headers().frameOptions().sameOrigin();

        http.csrf().disable()
            .headers().frameOptions().disable(); //h2-console 화면을 사용하기 위해 해당 옵션들을 disable
        http
            .authorizeRequests()
            // 허용하는 url
            .antMatchers("/resources/**","/loginError").permitAll()
            .antMatchers("/main").hasAuthority("USER") // USER 권한이 있는 사용자만 접근 허용
            .antMatchers("/login").permitAll() // login페이지는 접근 허용
            .anyRequest().authenticated();

        http.formLogin()
            .loginPage("/login")
            .successHandler(new CustomAuthenticationSuccess()) // 로그인 성공 핸들러
            .failureHandler(new CustomAuthenticationFailure()) // 로그인 실패 핸들러
            .loginProcessingUrl("/login/auth");



        //로그아웃
        http.logout()
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .logoutSuccessUrl("/login");            //로그아웃 후 보내질 페이지

        //접근권한 없을시 페이지
        http.exceptionHandling().accessDeniedHandler(customAuthenticationDenine);
    }

    // 권한 처리
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        auth.authenticationProvider(customAuthenticationProvider);
    }
}
