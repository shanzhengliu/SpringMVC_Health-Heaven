package com.sam.pojo;

import javax.persistence.*;

@Entity
@Table(name = "5619_write", schema = "elec5619", catalog = "")
public class WriteEntity {
    private int userId;
    private int postId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_ID", updatable = false, nullable = false)
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
    @Basic
    @Column(name = "user_ID", nullable = false,updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
