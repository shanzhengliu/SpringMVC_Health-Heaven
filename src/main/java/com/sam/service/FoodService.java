package com.sam.service;

import com.sam.dao.FoodDao;
import com.sam.dao.FoodRecordDao;
import com.sam.pojo.FoodExpenseEntity;
import com.sam.pojo.FoodRecordEntity;

import java.util.List;

public class FoodService {

    public static List<FoodExpenseEntity> queryFoodByName(String foodName) {
        return FoodDao.queryFoodByName(foodName);
    }

    public static FoodRecordEntity insertFoodRecord(String name, int amount, String time, String date, int calorie,
                                                    int userId) {
        return FoodRecordDao.insertFoodRecord(name, amount, time, date, calorie, userId);

    }

    public static void removeFoodRecord(String time, String date) {
        FoodRecordDao.removeFoodRecord(time, date);
    }

    public static List<FoodRecordEntity> getFoodRecords(int userId) {
        return FoodRecordDao.getFoodRecords(userId);
    }

}
