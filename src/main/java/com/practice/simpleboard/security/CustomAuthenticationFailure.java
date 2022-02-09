package com.practice.simpleboard.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailure implements AuthenticationFailureHandler {

    private final String DEFAULT_FAILURE_URL = "/login?error=true";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String exceptionClas = exception.getClass().getSimpleName();
        String errorType = "";

//        if("UsernameNotFoundException".equals(exceptionClas)) {// 아이디 없음
//            errorType = LoginErrorType.USER_NOT_FOUND.getValue();
//        }else if("BadCredentialsException".equals(exceptionClas)) { // 비밀번호 실패
//            errorType =  LoginErrorType.USER_PASSWORD.getValue();
//        }

//        request.setAttribute("errorType", errorType);
//        request.setAttribute("username", request.getParameter("username"));
        request.setAttribute("errorType", exceptionClas);
        request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
    }
}