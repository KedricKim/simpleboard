package com.practice.simpleboard.controller;

import com.practice.simpleboard.vo.UserVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainContoroller {

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login/login");
        return modelAndView;
    }

    @RequestMapping("/login/denine")
    public ModelAndView logindenine(){
        ModelAndView modelAndView = new ModelAndView("login/denine");
        return modelAndView;
    }

    @RequestMapping("/main")
    public ModelAndView notice(@AuthenticationPrincipal UserVo userVo) {
        ModelAndView mav = new ModelAndView("main/main");
        mav.addObject("auth", userVo.getAuthorCode());

        return mav;
    }
}
