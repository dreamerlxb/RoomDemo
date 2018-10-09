package com.lxb.roomdemo

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * 作者：tiger on 2018/5/30 10:58
 */
@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getPosts(): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<Post>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg posts: Post)
}