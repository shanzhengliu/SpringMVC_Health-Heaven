package com.sam.pojo;


import javax.persistence.*;

@Entity
@Table(name = "5619_Posts", schema = "elec5619", catalog = "")
public class PostEntity {


    private String title;
    private String content;
    private String tag;
    private int id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_ID", updatable = false, nullable = false)
    public int getId() {
        return id;
    }



    @Basic
    @Column(name = "title", nullable = false, length = 300)
    public String getTitle() {
        return title;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }


    @Basic
    @Column(name = "tags", length = 150)
    public String getTag() {
        return tag;
    }




    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }


}
