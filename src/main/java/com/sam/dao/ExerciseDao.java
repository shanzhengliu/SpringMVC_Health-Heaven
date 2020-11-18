package com.sam.dao;

import com.sam.pojo.SportInfoEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ExerciseDao {

    private static SessionFactory sf;

    static {
        try {
            sf = new Configuration().configure().buildSessionFactory();

        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Initialize SessionFactory fail " + e);
        }
    }

    public static List<SportInfoEntity> querySportByName(String name) {
        Session session = sf.openSession();

        StringBuffer sb = new StringBuffer();
        sb.append(name);
        sb.append("%");

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SportInfoEntity> criteria = builder.createQuery(SportInfoEntity.class);
        Root<SportInfoEntity> sportEntity = criteria.from(SportInfoEntity.class);

        criteria.select(sportEntity);
        criteria.where(builder.like(sportEntity.get("sportName"), sb.toString()));

        Query<SportInfoEntity> query = session.createQuery(criteria);
        List<SportInfoEntity> results = query.getResultList();

        for (SportInfoEntity s : results) {

            System.out.println(s.getSportName()+":    "+s.getCost());
        }

        return results;
    }

}
