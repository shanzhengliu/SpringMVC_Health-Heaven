package com.sam.service.userservice;

import com.sam.dao.User.UserDao;
import com.sam.pojo.UserEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("UserServiceImp")
public class UserServiceImp implements UserService {
    @Resource(description = "UserDao")
     UserDao userDao;
    public UserEntity LoginValid(String Email, String Password)
    {
        if(Email!=null&&Password!=null) {
            return userDao.queryByEmailandPassword(Email, Password);
        }
        else
        {
            return null;
        }
    }

    public UserEntity Register(String email, String username, String password, double height, double weight, double age, double targetWeight, int targetDate, String sex)
    {
        return userDao.insert(email, username, password, height, weight, age, targetWeight, targetDate, sex);
    }

    @Transactional
    public boolean ResetPassword(String email, String newpassword)
    {

        return userDao.UpdateUserPassword(email,newpassword);

    }

    public Map githublogin(String code){
        RestTemplate restTemplate=new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("client_id", "Iv1.31d2086604f72df9");
        map.put("client_secret", "66dd3a0d460b1351191c473443e1833e28a4d518");
        map.put("state", "javaboy");
        map.put("code", code);
        map.put("redirect_uri", "http://localhost:8888/authorize");
        Map<String,String> resp = restTemplate.postForObject("https://github.com/login/oauth/access_token", map, Map.class);
        HttpHeaders httpheaders = new HttpHeaders();
        httpheaders.add("Authorization", "token " + resp.get("access_token"));
        HttpEntity<?> httpEntity = new HttpEntity<>(httpheaders);
        ResponseEntity<Map> exchange = restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, httpEntity, Map.class);
        System.out.println("exchange.getBody() = " + exchange.getBody());
        if(userDao.queryByEmail(exchange.getBody().get("email").toString())==null)
        {
            userDao.insert(exchange.getBody().get("email").toString(),exchange.getBody().get("name").toString(),"",0,0,0,0,0,"");
        }

        return exchange.getBody();

    }


    public UserEntity SettingPage(String email)
    {
        return userDao.queryByEmail(email);
    }





}
