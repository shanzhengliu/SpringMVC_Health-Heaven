package com.sam.controller;


import com.sam.pojo.PostEntity;
import com.sam.pojo.UserEntity;
import com.sam.service.PostService;
import com.sam.service.WriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommunityPage {
    @Autowired
    PostService postService;

    @RequestMapping("/community")
    public String index(HttpSession httpSession, HttpServletRequest request){
        UserEntity userinfo = (UserEntity) httpSession.getAttribute("USER_SESSION");
        if (userinfo != null) {
            request.setAttribute("username", userinfo.getUsername());
            request.setAttribute("email", userinfo.getEmail());
            request.setAttribute("formlogin", "none".toString());
            request.setAttribute("formsetting", "inline".toString());

            request.setAttribute("publish","inline".toString());

        } else {
            request.setAttribute("username", "null");
            request.setAttribute("email", "null");
            request.setAttribute("formlogin", "inline");
            request.setAttribute("formsetting", "none");

            request.setAttribute("publish","none");

        }
        return "views/Community";
    }
    @RequestMapping(value = "/updatePosts",method = RequestMethod.POST )
    @ResponseBody
    public List<PostEntity> updatePosts(){

        return PostService.showPosts();
    }
//add new
    @RequestMapping(value = "/publishPosts",method = RequestMethod.POST )
    @ResponseBody
    public boolean publishPosts(String Title,String Tag,String Content,HttpSession httpSession,HttpServletRequest request){
        UserEntity userinfo = (UserEntity) httpSession.getAttribute("USER_SESSION");
        if(userinfo!=null){

            PostService.addPost(Title,Tag,Content);
            WriteService.add(userinfo.getEmail());
            return true;
        }else {
            return false;
        }


    }

}
