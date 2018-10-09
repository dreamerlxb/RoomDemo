package com.lxb.roomdemo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * 作者：tiger on 2018/5/30 09:17
 */
@Dao
public interface UserDao {
    /**
     * 查询
     *
     * @return
     */
    @Query("SELECT * FROM user")
    public List<User> getAllUsers();

    /**
     * 添加
     *
     * @param users
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUser(User... users);

    /**
     * 更新
     *
     * @param users
     */
    @Update
    public void updateUser(User... users);

    /**
     * 删除
     *
     * @param users
     */
    @Delete
    public void deleteUser(User... users);
}
