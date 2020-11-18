package com.sam.dao;

import com.sam.pojo.UserEntity;
import com.sam.pojo.WriteEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;

import java.util.List;

public class WriteDao {
    private static SessionFactory sf;

    static {
        try {
            sf = new Configuration().configure().buildSessionFactory();

        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Initialize SessionFactory fail " + e);
        }
    }
    public static WriteEntity addWirte(String email){

        Session session=sf.openSession();
        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Expression.like("email",email));
        List<UserEntity> user=criteria.list();

        WriteEntity w=new WriteEntity();
        w.setUserId(user.get(0).getUserId());
        session.beginTransaction();
        try {
            session.save(w);
        }catch (HibernateException e){
            return null;
        }
        session.getTransaction().commit();
        return w;

    }

}
