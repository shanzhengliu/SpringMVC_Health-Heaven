package com.sam.dao;

import com.sam.pojo.FoodRecordEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class FoodRecordDao {

    private static SessionFactory sf;

    static {
        try {
            sf = new Configuration().configure().buildSessionFactory();

        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Initialize SessionFactory fail " + e);
        }
    }

    public static FoodRecordEntity insertFoodRecord(String name, int amount, String time, String date, int calorie,
                                                    int userId) {
        FoodRecordEntity fr = new FoodRecordEntity();
        fr.setAmount(amount);
        fr.setCalorie(calorie);
        fr.setDate(date);
        fr.setTime(time);
        fr.setUserId(userId);
        fr.setFoodname(name);

        Session session = sf.openSession();
        session.beginTransaction();
        try {
            session.save(fr);
        } catch (HibernateException e) {
            System.out.println(e);
            return null;
        }
        session.getTransaction().commit();
        return fr;
    }

    public static void removeFoodRecord(String time, String date) {
        System.out.println(time + "    " + date);
        Session session = sf.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaDelete<FoodRecordEntity> criteriaDelete = builder.createCriteriaDelete(FoodRecordEntity.class);
        Root<FoodRecordEntity> foodRecordEntity = criteriaDelete.from(FoodRecordEntity.class);
        criteriaDelete.where(builder.like(foodRecordEntity.get("time"), time));
        criteriaDelete.where(builder.like(foodRecordEntity.get("date"), date));

        Transaction transaction = session.beginTransaction();
        int count = session.createQuery(criteriaDelete).executeUpdate();
        System.out.println("Deleted records number: " + count);
        transaction.commit();

    }

    public static List<FoodRecordEntity> getFoodRecords(int userId) {
        Session session = sf.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<FoodRecordEntity> criteria = builder.createQuery(FoodRecordEntity.class);
        Root<FoodRecordEntity> foodRecordEntity = criteria.from(FoodRecordEntity.class);

        criteria.select(foodRecordEntity);
        criteria.where(builder.equal(foodRecordEntity.get("userId"), userId));

        Query<FoodRecordEntity> query = session.createQuery(criteria);
        List<FoodRecordEntity> results = query.getResultList();

        return results;
    }

}
