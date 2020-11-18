package com.sam.service;

import com.sam.dao.PostDao;
import com.sam.pojo.PostEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("PostService")
public class PostService {

    public static List<PostEntity> showPosts(){return PostDao.loadPosts();}

    public static PostEntity addPost(String title, String tag, String content){
        return PostDao.insetPost(title, tag, content);
    }


}
