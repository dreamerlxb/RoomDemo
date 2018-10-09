package com.lxb.roomdemo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * 作者：tiger on 2018/5/30 12:15
 */
@Entity(tableName = "post", indices = {
        @Index(value = "user_id")
})
public class Post {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String content;

    @ColumnInfo(name = "user_id")
    private int userId;
//    private Date created;

    @Ignore
    public Post(int id, int userId, String title) {
        this.id = id;
        this.title = title;
        this.userId = userId;
    }

    @Ignore
    public Post(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    @Ignore
    public Post(int id) {
        this.id = id;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

//    public Date getCreated() {
//        return created;
//    }
//
//    public void setCreated(Date created) {
//        this.created = created;
//    }
}
