package com.sam.dao;

import com.sam.pojo.PostEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class PostDao {

    private static SessionFactory sf;

    static {
        try {
            sf = new Configuration().configure().buildSessionFactory();

        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Initialize SessionFactory fail " + e);
        }
    }

    public static List<PostEntity> loadPosts(){

        Session session=sf.openSession();
        try {
            return session.createCriteria(PostEntity.class).list();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public static PostEntity insetPost(String title, String tag, String content){
        PostEntity post = new PostEntity();
        post.setTitle(title);
        post.setContent(content);

        post.setTag(tag);
        Session session = sf.openSession();
        session.beginTransaction();
        try{
            session.save(post);
        }catch (HibernateException e){
            return null;
        }
        session.getTransaction().commit();
        return post;
    }

}
