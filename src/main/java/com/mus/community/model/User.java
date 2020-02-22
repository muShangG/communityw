package com.mus.community.model;

import lombok.Data;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-02-19 15:43:03
 */
@Data
public class User implements Serializable {

    
    private Integer id;
    
    private String accountId;
    
    private String name;
    
    private String token;
    
    private Long gmtCreat;
    
    private Long gmtModified;

    private String  avatar_url;



}