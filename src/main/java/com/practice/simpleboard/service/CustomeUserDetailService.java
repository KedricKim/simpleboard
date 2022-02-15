package com.practice.simpleboard.service;

import com.practice.simpleboard.repository.UserRepository;
import com.practice.simpleboard.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserVo member = userRepository.findByUserId(userId);
        if(member==null) {
            throw new UsernameNotFoundException(userId);
        }
        return member;
    }

}
