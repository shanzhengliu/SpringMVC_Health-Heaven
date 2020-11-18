package com.sam.dao;

import com.sam.pojo.FoodExpenseEntity;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import javax.persistence.criteria.*;
import java.util.List;


public class FoodDao {

    private static SessionFactory sf;

    static {
        try {
            sf = new Configuration().configure().buildSessionFactory();

        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Initialize SessionFactory fail " + e);
        }
    }

    public static List<FoodExpenseEntity> queryFoodByName(String name) {
        Session session = sf.openSession();

        StringBuffer sb = new StringBuffer();
        sb.append(name);
        sb.append("%");

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<FoodExpenseEntity> criteria = builder.createQuery(FoodExpenseEntity.class);
        Root<FoodExpenseEntity> foodEntity = criteria.from(FoodExpenseEntity.class);

        criteria.select(foodEntity);
        criteria.where(builder.like(foodEntity.get("foodname"), sb.toString()));

        Query<FoodExpenseEntity> query = session.createQuery(criteria);
        List<FoodExpenseEntity> results = query.getResultList();

        return results;
    }

}
