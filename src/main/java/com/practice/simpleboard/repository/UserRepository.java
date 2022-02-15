package com.practice.simpleboard.repository;

import com.practice.simpleboard.vo.UserVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserVo, Integer> {

    // query method 사용
    UserVo findByUserId(String userId);

    // query annotation 사용
    @Query(value = "from board_user where userId=:userId")
    UserVo getUser(String userId);

}