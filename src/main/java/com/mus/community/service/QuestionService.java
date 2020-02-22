package com.mus.community.service;

import com.mus.community.mapper.QuestionMapper;
import com.mus.community.mapper.UserMapper;
import com.mus.community.model.Question_User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    public List<Question_User> list() {
        return null;
    }
}
