package com.lxb.roomdemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * 作者：tiger on 2018/5/30 09:18
 */
@Database(entities = {User.class, Post.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
    public abstract PostDao getPostDao();
}
