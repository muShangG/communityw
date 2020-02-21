package com.mus.community.controller;

import com.mus.community.dto.AccessTokenDTO;
import com.mus.community.dto.GitHubUser;
import com.mus.community.mapper.UserMapper;
import com.mus.community.model.User;
import com.mus.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;
    /*获取配置文件的属性注解*/
    @Value("${github.Client_id}")
    private String Client_id;
    @Value("${github.Client_secret}")
    private String Client_secret;
    @Value("${github.Redirect_uri}")
    private String Redirect_uri;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(Client_id);
        accessTokenDTO.setClient_secret("71861b1bc2be4cf8f3d8e1f0185e0a85fd7fee00");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:81/callback");
        accessTokenDTO.setState(state);
        String accessTokken = gitHubProvider.getAccessTokken(accessTokenDTO);
        GitHubUser getuser = gitHubProvider.getuser(accessTokken);
        if (getuser != null && getuser.getId()!= null) {
            //登录成功写入cookie和session
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(getuser.getName());
            user.setAccountId(String.valueOf(getuser.getId()));
            user.setGmtCreat(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreat());
            userMapper.insert(user);
            //写入cookie
            response.addCookie(new Cookie("token",token));
            //写入session
            //request.getSession().setAttribute("user",getuser);
            return "redirect:/";
        } else {
            return "redirect:/";
        }

    }
}
