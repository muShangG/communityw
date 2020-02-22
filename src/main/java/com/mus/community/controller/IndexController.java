package com.mus.community.controller;

import com.mus.community.mapper.QuestionMapper;
import com.mus.community.mapper.UserMapper;
import com.mus.community.model.Question;
import com.mus.community.model.Question_User;
import com.mus.community.model.User;
import com.mus.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;


    @GetMapping("/")
    public String Index(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<Map<String, Object>> maps = questionMapper.ListMap();
        model.addAttribute("map", maps);

        System.out.println(maps);
        return "Index";
    }
}
