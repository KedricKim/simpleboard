package com.practice.simpleboard.repository;

import com.practice.simpleboard.vo.UserVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserVo, Integer> {

    @Query(value = "from board_user where userId=:userId")
    UserVo findByUsername(String userId);

}