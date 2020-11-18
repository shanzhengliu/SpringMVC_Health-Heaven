package com.sam.controller;


import com.sam.pojo.ExerciseRecordEntity;
import com.sam.pojo.FoodRecordEntity;
import com.sam.pojo.UserEntity;
import com.sam.service.FoodService;
import com.sam.service.SportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/report")
public class ExerciseAndFoodReportPage {
    @RequestMapping("/showCharts")
    public ModelAndView showCharts(HttpSession httpSession, HttpServletRequest request){
        ModelAndView mv = new ModelAndView("views/ExerciseAndFoodReport");
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
        return mv;
    }

    @RequestMapping("/getChartsData")
    @ResponseBody
    public Map<String,Map<String,Integer>> getChartsData(HttpSession httpSession){
        Map<String,Map<String,Integer>> data = new HashMap<>();
        UserEntity userinfo = (UserEntity) httpSession.getAttribute("USER_SESSION");
        if (userinfo != null) {
            // construct chart one (x represents data, while y represents diff between (food - exercise))
            Map<String, Integer> chart_one = new HashMap<>();
            Map<String, Integer> chart_two = new HashMap<>();
            Map<String, Integer> chart_three = new HashMap<>();

            List<FoodRecordEntity>  foodRecords = FoodService.getFoodRecords(userinfo.getUserId());
            List<ExerciseRecordEntity> exerciseRecords = SportService.getExerciseRecords(userinfo.getUserId());

            for(FoodRecordEntity foodRecord : foodRecords){
                if(!chart_one.containsKey(foodRecord.getDate())){
                    chart_one.put(foodRecord.getDate(),foodRecord.getCalorie());
                }else{
                    chart_one.put(foodRecord.getDate(),foodRecord.getCalorie() + chart_one.get(foodRecord.getDate()));
                }

                if(!chart_two.containsKey(foodRecord.getFoodname())){
                    chart_two.put(foodRecord.getFoodname(),foodRecord.getCalorie()/foodRecord.getAmount());
                }else{
                    if(foodRecord.getCalorie()/foodRecord.getAmount() > chart_two.get(foodRecord.getFoodname()))
                        chart_two.put(foodRecord.getFoodname(), foodRecord.getCalorie()/foodRecord.getAmount());
                }
            }

            for(ExerciseRecordEntity exerciseRecordEntity : exerciseRecords){
                if(!chart_one.containsKey(exerciseRecordEntity.getDate())){
                    chart_one.put(exerciseRecordEntity.getDate(), - exerciseRecordEntity.getCalorie());
                }else{
                    chart_one.put(exerciseRecordEntity.getDate(), chart_one.get(exerciseRecordEntity.getDate()) - exerciseRecordEntity.getCalorie());
                }
                if(!chart_three.containsKey(exerciseRecordEntity.getSportName())){
                    chart_three.put(exerciseRecordEntity.getSportName(), exerciseRecordEntity.getCalorie()/exerciseRecordEntity.getDuration());
                }else{
                    if(exerciseRecordEntity.getCalorie()/exerciseRecordEntity.getDuration() < chart_three.get(exerciseRecordEntity.getSportName()))
                        chart_three.put(exerciseRecordEntity.getSportName(),exerciseRecordEntity.getCalorie()/exerciseRecordEntity.getDuration());
                }
            }
            data.put("chart_one", chart_one);
            data.put("chart_two", chart_two);
            data.put("chart_three", chart_three);
        }
       return data;
    }


}
