package com.mus.community.model;

import lombok.Data;

import java.io.Serializable;

/**
 * (Question)实体类
 *
 * @author makejava
 * @since 2020-02-21 13:38:03
 */
@Data
public class Question implements Serializable {
    
    private String title;
    
    private String desc;
    
    private Long gmtCreate;
    
    private Long gmtModified;
    
    private Integer commentCount;
    
    private Integer  viewCount;
    
    private Integer likeCount;
    
    private String tag;
    
    private Integer creator;





}