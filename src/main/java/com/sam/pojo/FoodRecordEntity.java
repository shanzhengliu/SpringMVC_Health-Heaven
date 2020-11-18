package com.sam.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "5619_FoodRecord", schema = "elec5619", catalog = "")
public class FoodRecordEntity {
    private int id;
    private String time;
    private String date;
    private int amount;
    private String foodname;
    private int userId;
    private int calorie;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "foodname")
    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "calorie")
    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodRecordEntity that = (FoodRecordEntity) o;
        return id == that.id &&
                amount == that.amount &&
                userId == that.userId &&
                calorie == that.calorie &&
                Objects.equals(time, that.time) &&
                Objects.equals(date, that.date) &&
                Objects.equals(foodname, that.foodname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, date, amount, foodname, userId, calorie);
    }
}
