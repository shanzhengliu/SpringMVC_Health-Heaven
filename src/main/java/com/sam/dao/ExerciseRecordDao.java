package com.sam.dao;

import com.sam.pojo.ExerciseRecordEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ExerciseRecordDao {
    private static SessionFactory sf;

    static {
        try {
            sf = new Configuration().configure().buildSessionFactory();

        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Initialize SessionFactory fail " + e);
        }
    }

    public static ExerciseRecordEntity insertExerciseRecord(String name, int amount, String time, String date, int calorie,
                                                    int userId) {
        ExerciseRecordEntity er = new ExerciseRecordEntity();
        er.setDuration(amount);
        er.setCalorie(calorie);
        er.setDate(date);
        er.setTime(time);
        er.setUserId(userId);
        er.setSportName(name);

        Session session = sf.openSession();
        session.beginTransaction();
        try {
            session.save(er);
        } catch (HibernateException e) {
            System.out.println(e);
            return null;
        }
        session.getTransaction().commit();
        return er;
    }

    public static void removeExerciseRecord(String time, String date) {
        Session session = sf.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaDelete<ExerciseRecordEntity> criteriaDelete = builder.createCriteriaDelete(ExerciseRecordEntity.class);
        Root<ExerciseRecordEntity> exerciseRecordEntity = criteriaDelete.from(ExerciseRecordEntity.class);
        criteriaDelete.where(builder.equal(exerciseRecordEntity.get("time"), time));
        criteriaDelete.where(builder.equal(exerciseRecordEntity.get("date"), date));

        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaDelete).executeUpdate();
        transaction.commit();

    }

    public static List<ExerciseRecordEntity> getExerciseRecords(int userId) {
        Session session = sf.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExerciseRecordEntity> criteria = builder.createQuery(ExerciseRecordEntity.class);
        Root<ExerciseRecordEntity> exerciseRecordEntity = criteria.from(ExerciseRecordEntity.class);

        criteria.select(exerciseRecordEntity);
        criteria.where(builder.equal(exerciseRecordEntity.get("userId"), userId));

        Query<ExerciseRecordEntity> query = session.createQuery(criteria);
        List<ExerciseRecordEntity> results = query.getResultList();

        return results;
    }
}
