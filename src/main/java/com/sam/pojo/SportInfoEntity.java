package com.sam.pojo;

import javax.persistence.*;

@Entity
@Table(name = "5619_SportInfo", schema = "elec5619", catalog = "")
public class SportInfoEntity {
    private int sportId;
    private int cost;
    private String sportName;
    private String sportType;
    private String sportDetail;
    private String sportComment;

    @Id
    @Column(name = "Sport_ID", nullable = false)
    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    @Basic
    @Column(name = "sport_name", nullable = false, length = -1)
    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    @Basic
    @Column(name = "sport_type", nullable = false, length = -1)
    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    @Basic
    @Column(name = "sport_detail", nullable = false, length = -1)
    public String getSportDetail() {
        return sportDetail;
    }

    public void setSportDetail(String sportDetail) {
        this.sportDetail = sportDetail;
    }

    @Basic
    @Column(name = "sport_comment", nullable = false, length = -1)
    public String getSportComment() {
        return sportComment;
    }

    public void setSportComment(String sportComment) {
        this.sportComment = sportComment;
    }

    @Basic
    @Column(name = "cost", nullable = false, length = -1)
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SportInfoEntity that = (SportInfoEntity) o;

        if (sportId != that.sportId) return false;
        if (sportName != null ? !sportName.equals(that.sportName) : that.sportName != null) return false;
        if (sportType != null ? !sportType.equals(that.sportType) : that.sportType != null) return false;
        if (sportDetail != null ? !sportDetail.equals(that.sportDetail) : that.sportDetail != null) return false;
        if (sportComment != null ? !sportComment.equals(that.sportComment) : that.sportComment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sportId;
        result = 31 * result + (sportName != null ? sportName.hashCode() : 0);
        result = 31 * result + (sportType != null ? sportType.hashCode() : 0);
        result = 31 * result + (sportDetail != null ? sportDetail.hashCode() : 0);
        result = 31 * result + (sportComment != null ? sportComment.hashCode() : 0);
        return result;
    }
}
