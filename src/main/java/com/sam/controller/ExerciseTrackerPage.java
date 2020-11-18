package com.sam.controller;

import com.sam.pojo.ExerciseRecordEntity;
import com.sam.pojo.SportInfoEntity;
import com.sam.pojo.UserEntity;
import com.sam.service.SportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

public class ExerciseTrackerPage {

    @RequestMapping("/exercise-tracker")
    public String index(HttpSession httpSession, HttpServletRequest request) {
        UserEntity userinfo = (UserEntity) httpSession.getAttribute("USER_SESSION");
        if (userinfo != null) {
            request.setAttribute("username", userinfo.getUsername());
            request.setAttribute("email", userinfo.getEmail());
            request.setAttribute("formlogin", "none".toString());
            request.setAttribute("formsetting", "inline".toString());

        } else {
            request.setAttribute("username", "null");
            request.setAttribute("email", "null");
            request.setAttribute("formlogin", "inline");
            request.setAttribute("formsetting", "none");

        }
        return "views/ExerciseTracker";
    }

    @ResponseBody
    @RequestMapping(value="/ExerciseNameQuery", method= RequestMethod.POST)
    public List<SportInfoEntity> queryExerciseName(String sportName) {
        System.out.println("Sport Name is: " + sportName);
        List<SportInfoEntity> sportList = SportService.querySportByName(sportName);
        System.out.println("Total: " + sportList.size());
        return sportList;
    }

    @ResponseBody
    @RequestMapping(value="/AddExerciseRecord", method= RequestMethod.POST)
    public int addFoodRecord(String foodname, int amount, String time, String date, int calorie,
                             HttpSession httpSession, HttpServletRequest request) {
        UserEntity userinfo = (UserEntity) httpSession.getAttribute("USER_SESSION");

        ExerciseRecordEntity er = SportService.insertExerciseRecord(foodname, amount, time, date, calorie, userinfo.getUserId());

        if (er != null) {
            System.out.println("Exercise Record -- Insert Success.");
            return 200;
        }
        else {
            System.out.println("Exercise Record -- Insert Error.");
            return 400;
        }
    }

    @ResponseBody
    @RequestMapping("/RemoveExerciseRecord")
    public int removeFoodRecord(String time, String date, HttpSession httpSession, HttpServletRequest request) {
        SportService.removeExerciseRecord(time, date);
        return 200;
    }

    @ResponseBody
    @RequestMapping(value="/GetAllExerciseRecords", method= RequestMethod.POST)
    public List<ExerciseRecordEntity> getAllExerciseRecords(HttpSession httpSession, HttpServletRequest request) {
        UserEntity userinfo = (UserEntity) httpSession.getAttribute("USER_SESSION");
        List<ExerciseRecordEntity> res = SportService.getExerciseRecords(userinfo.getUserId());
        return res;
    }

}
