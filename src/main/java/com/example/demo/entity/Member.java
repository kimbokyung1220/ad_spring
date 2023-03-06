package com.example.demo.entity;

import javax.persistence.Id;

/**
 * 회원
 */
public class Member {
    @Id
    private String memberId;
    private String pwd;
    private String ROLE_GROUP;
}
