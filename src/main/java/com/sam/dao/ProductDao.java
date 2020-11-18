package com.sam.dao;

import com.sam.pojo.ProductEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDao {
    private static SessionFactory sf;

    static {
        try {
            sf = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Initialize SessionFactory fail " + e);
        }
    }

    public static List<ProductEntity> listAllProducts(){
        Session session = sf.openSession();
        try
        {
            return session.createCriteria(ProductEntity.class).list();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static HashSet<String> listBrands(){
        Session session = sf.openSession();
        HashSet<String> brands = new HashSet<>();
        try
        {
            List<ProductEntity> products = session.createCriteria(ProductEntity.class).list();
            products.forEach( productEntity -> {
                brands.add(productEntity.getName().split("\\|")[0]);
            });
            return brands;
        } catch (Exception e) {
            return new HashSet<>();
        }
    }
    public static HashSet<String> listOrgans(){
        Session session = sf.openSession();
        HashSet<String> brands = new HashSet<>();
        try
        {
            List<ProductEntity> products = session.createCriteria(ProductEntity.class).list();
            products.forEach( productEntity -> {
                brands.add(productEntity.getName().split("\\|")[1]);
            });
            return brands;
        } catch (Exception e) {
            return new HashSet<>();
        }
    }
    public static List<ProductEntity> filterProducts(String firstLevelFilter,
                                                     String secondLevelFilter){
        Session session = sf.openSession();
        try
        {
            List<ProductEntity> raw = session.createCriteria(ProductEntity.class).list();
            return raw.stream()
                      .filter(productEntity ->
                              productEntity.getName().split("\\|")[0].equals(firstLevelFilter)
                                      ||firstLevelFilter.equals("All"))
                      .filter(productEntity ->
                             productEntity.getName().split("\\|")[1].equals(secondLevelFilter)
                                     ||secondLevelFilter.equals("All"))
                     .collect(Collectors.toList());

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
