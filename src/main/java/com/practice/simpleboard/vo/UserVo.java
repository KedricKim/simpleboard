package com.practice.simpleboard.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="board_user")
//@DynamicUpdate
//@DynamicInsert
public class UserVo extends CustomUserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    private String userId;
    private String userNm;
    private String password;
    private String registDt;
    private String authorCode;

}
