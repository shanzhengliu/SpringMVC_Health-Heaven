package com.sam.pojo;

import javax.persistence.*;

@Entity
@Table(name = "5619_FoodExpense", schema = "elec5619", catalog = "")
public class FoodExpenseEntity {
    private int foodExpenseId;
    private String foodname;
    private String type;
    private double cost;

    @Id
    @Column(name = "FoodExpense_ID", nullable = false)
    public int getFoodExpenseId() {
        return foodExpenseId;
    }

    public void setFoodExpenseId(int foodExpenseId) {
        this.foodExpenseId = foodExpenseId;
    }

    @Basic
    @Column(name = "foodname", nullable = false, length = 100)
    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    @Basic
    @Column(name = "type", nullable = false, length = -1)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "cost", nullable = false, precision = 0)
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodExpenseEntity that = (FoodExpenseEntity) o;

        if (foodExpenseId != that.foodExpenseId) return false;
        if (Double.compare(that.cost, cost) != 0) return false;
        if (foodname != null ? !foodname.equals(that.foodname) : that.foodname != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = foodExpenseId;
        result = 31 * result + (foodname != null ? foodname.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
