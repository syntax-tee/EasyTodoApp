package com.app.taiye.todocleanarchitecture.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.taiye.todocleanarchitecture.data.model.ToDoData

interface ToDoDao {


    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData():LiveData<List<ToDoData>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend  fun insertData(toDoData: ToDoData)




}