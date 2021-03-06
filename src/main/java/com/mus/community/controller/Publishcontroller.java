package com.mus.community.controller;

import com.mus.community.mapper.QuestionMapper;
import com.mus.community.mapper.UserMapper;
import com.mus.community.model.Question;
import com.mus.community.model.User;
import jdk.nashorn.internal.ir.IfNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class Publishcontroller {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String dopublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "desc", required = false) String desc,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model

    ) {
        model.addAttribute("title", title);
        model.addAttribute("desc", desc);
        model.addAttribute("tag", tag);
        if (title == null || title == "") {
            model.addAttribute("error", "标题不为空");
            return "publish";
        }
        if (desc == null || desc == "") {
            model.addAttribute("error", "问题不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
            if (user == null) {
                model.addAttribute("error", "用户还未登录");
                return "publish";
            }
            Question question = new Question();
            question.setTitle(title);
            question.setDesc(desc);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
            return "redirect:/";
        } else {
            model.addAttribute("error", "用户还未登录");
            return "publish";
        }


    }
}
