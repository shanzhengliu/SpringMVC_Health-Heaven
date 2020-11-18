package com.sam.controller;

import com.sam.pojo.UserEntity;
//import com.sam.service.UserService;
import com.sam.service.userservice.UserService;
import com.sam.service.userservice.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller("LoginPage")
public class LoginPage {
    @Autowired
    UserService userService;


    @RequestMapping("/")
    public String index(HttpSession httpSession, HttpServletRequest request) {
        UserEntity userinfo = (UserEntity) httpSession.getAttribute("USER_SESSION");
        if (userinfo != null) {
            request.setAttribute("username", userinfo.getUsername());
            request.setAttribute("email", userinfo.getEmail());
            request.setAttribute("formlogin", "none".toString());
            request.setAttribute("formsetting", "inline".toString());

        } else {
            request.setAttribute("username", "null");
            request.setAttribute("email", "null");
            request.setAttribute("formlogin", "inline");
            request.setAttribute("formsetting", "none");

        }
        return "views/index";


    }

    @RequestMapping("/login")
    public String login(String Email, String Password, HttpSession httpSession) {
        httpSession.setAttribute("USER_SESSION", userService.LoginValid(Email,Password));
        return "redirect: /";

    }

    @RequestMapping("/setting")
     public String Setting(HttpSession httpSession,HttpServletRequest request)
    {
        UserEntity userinfo = (UserEntity) httpSession.getAttribute("USER_SESSION");
        if (userinfo != null) {
            request.setAttribute("username", userinfo.getUsername());
            request.setAttribute("email", userinfo.getEmail());
            request.setAttribute("formlogin", "none".toString());
            request.setAttribute("formsetting", "inline".toString());

        } else {
            request.setAttribute("username", "null");
            request.setAttribute("email", "null");
            request.setAttribute("formlogin", "inline");
            request.setAttribute("formsetting", "none");

        }

        return "views/Setting";

    }

    @RequestMapping("/MainPage")
    public String Mainpage(HttpSession httpSession, HttpServletRequest request) {

        UserEntity userinfo = (UserEntity) httpSession.getAttribute("USER_SESSION");
        if (userinfo != null) {
            request.setAttribute("username", userinfo.getUsername());
            request.setAttribute("email", userinfo.getEmail());

        }
        return "redirect: /";

    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession, HttpServletRequest request) {
        httpSession.removeAttribute("USER_SESSION");
        return "redirect: /";
    }

    @RequestMapping("/register")
    public ResponseEntity register(String SignUserName, String SignEmail, String SignPassword, String SignGender, int SignAge, float SignWeight, int SignTargetDate, float SignTargetWeight, float SignHeight, HttpSession httpSession, HttpServletRequest request) {
       UserEntity  newuser= userService.Register(SignEmail,SignUserName,SignPassword,SignHeight,SignWeight,SignAge,SignTargetWeight,SignTargetDate,SignGender);
        if(newuser!=null)
        {
            httpSession.setAttribute("USER_SESSION", newuser);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/update")
    public String update( String username,String newpassword,HttpSession httpSession)
    {   UserEntity userinfo = (UserEntity) httpSession.getAttribute("USER_SESSION");
        userService.ResetPassword(userinfo.getEmail(),newpassword);

        return "redirect: /";

    }

    @RequestMapping("/authorize")
    public String logingithub(String code,HttpServletRequest request,HttpSession httpSession)
    {
        Map userinfo =userService.githublogin(code);


        if (userinfo != null) {
            UserEntity user = new UserEntity();


            request.setAttribute("username", userinfo.get("name").toString());
            request.setAttribute("email", userinfo.get("email").toString());
            request.setAttribute("formlogin", "none".toString());
            request.setAttribute("formsetting", "inline".toString());
            user.setEmail(userinfo.get("email").toString());
            user.setUsername(userinfo.get("name").toString());

            httpSession.setAttribute("USER_SESSION", user );

        } else {
            request.setAttribute("username", "null");
            request.setAttribute("email", "null");
            request.setAttribute("formlogin", "inline");
            request.setAttribute("formsetting", "none");

        }
        return "redirect: /";
    }
}
