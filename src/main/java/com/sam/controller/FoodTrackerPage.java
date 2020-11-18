package com.sam.controller;

import com.sam.pojo.FoodExpenseEntity;
import com.sam.pojo.FoodRecordEntity;
import com.sam.pojo.UserEntity;
import com.sam.service.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

public class FoodTrackerPage {

    @RequestMapping("/food-tracker")
    public String foodTracker(HttpSession httpSession, HttpServletRequest request) {
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
        return "views/FoodTracker";
    }

    @ResponseBody
    @RequestMapping(value="/FoodNameQuery", method= RequestMethod.POST)
    public List<FoodExpenseEntity> queryFoodName(String foodname) {
        System.out.println("Food Name is: " + foodname);
        List<FoodExpenseEntity> foodList = FoodService.queryFoodByName(foodname);
        System.out.println("Total:  " + foodList.size());
        return foodList;
    }

    @ResponseBody
    @RequestMapping(value="/AddFoodRecord", method= RequestMethod.POST)
    public int addFoodRecord(String foodname, int amount, String time, String date, int calorie,
                                        HttpSession httpSession, HttpServletRequest request) {
        UserEntity userinfo = (UserEntity) httpSession.getAttribute("USER_SESSION");

        FoodRecordEntity fr = FoodService.insertFoodRecord(foodname, amount, time, date, calorie, userinfo.getUserId());

        if (fr != null) {
            System.out.println("Food Record -- Insert Success.");
            return 200;
        }
        else {
            System.out.println("Food Record -- Insert Error.");
            return 400;
        }
    }

    @ResponseBody
    @RequestMapping("/RemoveFoodRecord")
    public int removeFoodRecord(String time, String date, HttpSession httpSession, HttpServletRequest request) {

        FoodService.removeFoodRecord(time, date);

        return 200;
    }

    @ResponseBody
    @RequestMapping(value="/GetAllFoodRecords", method= RequestMethod.POST)
    public List<FoodRecordEntity> getAllFoodRecords(HttpSession httpSession, HttpServletRequest request) {
        UserEntity userinfo = (UserEntity) httpSession.getAttribute("USER_SESSION");
        List<FoodRecordEntity> res = FoodService.getFoodRecords(userinfo.getUserId());
        return res;
    }

}
