package com.sam.service;

import com.sam.dao.ProductDao;

import com.sam.pojo.ProductEntity;
import org.springframework.ui.ModelMap;

import java.util.HashSet;
import java.util.List;

public class ProductService {
    public static List<ProductEntity> showProducts() {
        return ProductDao.listAllProducts();
    }

    public static HashSet<String> showBrands(){
        return ProductDao.listBrands();
    }
    public static HashSet<String> showOrgans(){
        return ProductDao.listOrgans();
    }
    public static ModelMap prepareRenderData(){
        ModelMap mm = new ModelMap();
        mm.addAttribute("products",showProducts());
        mm.addAttribute("brands",showBrands());
        mm.addAttribute("organs",showOrgans());
        return mm;
    }
    public static List<ProductEntity> filterProducts(String firstLevelFilter,
                                                     String secondLevelFilter) {
        return ProductDao.filterProducts(firstLevelFilter, secondLevelFilter);
    }

}
