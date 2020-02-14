package com.mus.community;

import com.mus.community.dto.AccessTokenDTO;
import com.mus.community.dto.GitHubUser;
import com.mus.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;
    /*获取配置文件的属性注解*/
    @Value("${github.Client_id}")
    private  String Client_id;
    @Value("${github.Client_secret}")
    private  String Client_secret;
    @Value("${github.Redirect_uri}")
    private  String Redirect_uri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(Client_id);
        accessTokenDTO.setClient_secret("71861b1bc2be4cf8f3d8e1f0185e0a85fd7fee00");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:81/callback");
        accessTokenDTO.setState(state);
        String accessTokken = gitHubProvider.getAccessTokken(accessTokenDTO);
        GitHubUser getuser = gitHubProvider.getuser(accessTokken);
        System.out.println(getuser.getName());
        return "index";
    }
}
