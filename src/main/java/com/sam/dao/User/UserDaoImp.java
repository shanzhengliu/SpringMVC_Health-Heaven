package com.sam.dao.User;
import com.sam.pojo.UserEntity;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

import org.springframework.util.DigestUtils;
@Repository( "UserDaoImp")
public class UserDaoImp implements UserDao {

    private static SessionFactory sf;

    static {
        try {
            sf = new Configuration().configure().buildSessionFactory();

        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Initialize SessionFactory fail " + e);
        }
    }


    public UserEntity insert( String email, String username, String password, double height, double weight, double age, double targetWeight, int targetDate, String sex)
    {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(new StringBuffer(md5Password).reverse().toString());
        user.setEmail(email);
        user.setAge(age);
        user.setHeight(height);
        user.setWeight(weight);
        user.setHistory("");
        user.setTargetDate(targetDate);
        user.setAge(age);
        user.setSex(sex);
        user.setTargetWeight(targetWeight);
        Session session = sf.openSession();
        session.beginTransaction();
        try {
            session.save(user);
        } catch (HibernateException e) {
            return null;
        }
        session.getTransaction().commit();
        return user;
    }



    public UserEntity queryByEmailandPassword(String Email, String Password)
    {
        Session session = sf.openSession();
        Criteria criteria = session.createCriteria(UserEntity.class);
        criteria.add(Expression.like("email",Email));
        String md5Password = DigestUtils.md5DigestAsHex(Password.getBytes());
        criteria.add(Expression.like("password",new StringBuffer(md5Password).reverse().toString()));
        List<UserEntity> user=criteria.list();
        if(user.size()>0)
        {
            return user.get(0);
        }
        else
        {
            return null;
        }

    }

    @Transactional
    public  boolean UpdateUserPassword(String Email,String NewPassword)
    {
        Session session = sf.openSession();
        String md5Password = DigestUtils.md5DigestAsHex(NewPassword.getBytes());
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<UserEntity> criteriaUpdate = builder.createCriteriaUpdate(UserEntity.class);
        Root<UserEntity> userEntity = criteriaUpdate.from(UserEntity.class);

        criteriaUpdate.set("password",new StringBuffer(md5Password).reverse().toString());
        criteriaUpdate.where(builder.equal(userEntity.get("email"), Email));


//        Query updateQuery = session.createQuery("UPDATE UserEntity t set t.password ='" + md5Password + "' where t.email ='" + Email+"'");
        try {
//            updateQuery.executeUpdate();
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            return false;
        }
        return true;





    }

    public UserEntity queryByEmail(String Email)
    {
        Session session = sf.openSession();
        Criteria criteria = session.createCriteria(UserEntity.class);

        criteria.add(Expression.like("email",Email));
        List<UserEntity> user=criteria.list();
        if(user.size()>0)
        {
            return user.get(0);
        }
        else
        {
            return null;
        }
    }


}
