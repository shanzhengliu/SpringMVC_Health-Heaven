package com.sam.pojo;

import javax.persistence.*;

@Entity
@Table(name = "5619_User", schema = "elec5619", catalog = "")
public class UserEntity {
    private int userId;
    private String email;
    private String username;
    private String password;
    private String history;
    private double height;
    private double weight;
    private double age;
    private double targetWeight;
    private int targetDate;
    private String sex;

    @Id
    @Column(name = "user_ID", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 150)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "username", nullable = false, length = -1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "history", nullable = false, length = -1)
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @Basic
    @Column(name = "height", nullable = false, precision = 0)
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Basic
    @Column(name = "weight", nullable = false, precision = 0)
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "age", nullable = false, precision = 0)
    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    @Basic
    @Column(name = "target_weight", nullable = false, precision = 0)
    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    @Basic
    @Column(name = "target_date", nullable = false)
    public int getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(int targetDate) {
        this.targetDate = targetDate;
    }

    @Basic
    @Column(name = "sex", nullable = false, length = -1)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (Double.compare(that.height, height) != 0) return false;
        if (Double.compare(that.weight, weight) != 0) return false;
        if (Double.compare(that.age, age) != 0) return false;
        if (Double.compare(that.targetWeight, targetWeight) != 0) return false;
        if (targetDate != that.targetDate) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (history != null ? !history.equals(that.history) : that.history != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = userId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (history != null ? history.hashCode() : 0);
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(age);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(targetWeight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + targetDate;
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        return result;
    }
}
