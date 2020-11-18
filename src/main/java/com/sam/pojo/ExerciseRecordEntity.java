package com.sam.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "5619_ExerciseRecord", schema = "elec5619", catalog = "")
public class ExerciseRecordEntity {
    private int id;
    private String sportName;
    private int duration;
    private int calorie;
    private int userId;
    private String time;
    private String date;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sportName")
    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    @Basic
    @Column(name = "duration")
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "calorie")
    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseRecordEntity that = (ExerciseRecordEntity) o;
        return id == that.id &&
                duration == that.duration &&
                calorie == that.calorie &&
                userId == that.userId &&
                Objects.equals(sportName, that.sportName) &&
                Objects.equals(time, that.time) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sportName, duration, calorie, userId, time, date);
    }
}
