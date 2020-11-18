package com.sam.service;

import com.sam.dao.ExerciseDao;
import com.sam.dao.ExerciseRecordDao;
import com.sam.pojo.ExerciseRecordEntity;
import com.sam.pojo.SportInfoEntity;

import java.util.List;

public class SportService {

    public static List<SportInfoEntity> querySportByName(String sportName) {
        return ExerciseDao.querySportByName(sportName);
    }

    public static ExerciseRecordEntity insertExerciseRecord(String name, int amount, String time, String date, int calorie,
                                                            int userId) {
        return ExerciseRecordDao.insertExerciseRecord(name, amount, time, date, calorie, userId);
    }

    public static void removeExerciseRecord(String time, String date) {
        ExerciseRecordDao.removeExerciseRecord(time, date);
    }

    public static List<ExerciseRecordEntity> getExerciseRecords(int userId) {
        return ExerciseRecordDao.getExerciseRecords(userId);
    }
}
