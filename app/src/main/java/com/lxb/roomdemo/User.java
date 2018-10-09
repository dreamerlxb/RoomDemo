package com.lxb.roomdemo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * 作者：tiger on 2018/5/30 09:16
 */

@Entity(tableName = "user", indices = {
        @Index(value = "user_id", unique = true)
})
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "user_id")
    private String userId;

    @ColumnInfo(name = "username")
    private String userName;

    @ColumnInfo(name = "age")
    private int age;

    @ColumnInfo(name = "updated")
    private long updated;

    @ColumnInfo(name = "created")
    private long created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updateTime) {
        this.updated = updateTime;
    }
}
