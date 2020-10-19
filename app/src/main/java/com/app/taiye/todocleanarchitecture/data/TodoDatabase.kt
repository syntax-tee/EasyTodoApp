package com.app.taiye.todocleanarchitecture.data

import android.content.Context
import androidx.room.*
import com.app.taiye.todocleanarchitecture.data.dao.ToDoDao
import com.app.taiye.todocleanarchitecture.data.model.ToDoData
import com.app.taiye.todocleanarchitecture.util.Converter


@Database(entities = [ToDoData::class], version = 2, exportSchema = false)
@TypeConverters(Converter::class)
abstract class  TodoDatabase : RoomDatabase() {

    abstract fun todoDao():ToDoDao


    companion object{

        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context):TodoDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(context
                    .applicationContext,
                TodoDatabase::class.java,
                "todo-database").build()
                INSTANCE = instance
                return  instance
            }
        }
    }


}