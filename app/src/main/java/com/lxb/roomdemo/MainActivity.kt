package com.lxb.roomdemo

import android.arch.persistence.db.SupportSQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.util.Log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        sample_text.text = stringFromJNI()

        thread {
            testDb()
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        val TAG = MainActivity::class.java.simpleName!!;

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    private fun testDb() {
        val appDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app-db").build()

        val postDao = appDatabase.postDao;
        postDao.insertAll(Post(1, 2, "post-01"), Post(2, 2, "post-02"))

        val gotPosts = postDao.getPosts()

        for (u in gotPosts) {
            Log.d(TAG, u.id.toString() + "," + u.content + "," + u.title + "," + u.userId)
        }


        val userDao = appDatabase.userDao
        val user = User()
        user.userName = "username"
        user.age = 12
        user.id = 2
        user.userId = "12"
        user.updated = System.currentTimeMillis()
        userDao.insertUser(user)

        readDatabase(userDao)
    }

    private fun readDatabase(dao: UserDao) {
        Log.d(TAG, "读数据库...")
        val users = dao.allUsers
        for (u in users) {
            Log.d(TAG, u.id.toString() + "," + u.userName + "," + u.age + "," + u.updated)
        }
        Log.d(TAG, "读数据库完毕.")
    }

    private fun writeDatabase(dao: UserDao, name: String, age: Int) {
        val user = User()
        user.userName = name
        user.age = age
        user.updated = System.currentTimeMillis()
        dao.insertUser(user)
    }

    private fun updateUser(dao: UserDao) {
        Log.d(TAG, "更新数据库...")
        val u = User()
        u.id = 2
        u.userName = "赵五"
        u.age = 20
        u.updated = System.currentTimeMillis()
        dao.updateUser(u)
        Log.d(TAG, "更新数据库完毕.")
    }

    private fun deleteUser(dao: UserDao, id: Int) {
        Log.d(TAG, "删除数据库...")
        val u = User()
        u.id = id
        dao.deleteUser(u)
        Log.d(TAG, "删除数据库完毕.")
    }

    /**
     * 测试数据迁移
     */
    private fun testM() {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD COLUMN created LONG")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }

        val appDatabase = Room
                .databaseBuilder(applicationContext, AppDatabase::class.java, "app-db")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build()
    }
}
