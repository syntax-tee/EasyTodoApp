package com.app.taiye.todocleanarchitecture.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.taiye.todocleanarchitecture.data.model.ToDoData


@Dao
interface ToDoDao {


    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData():LiveData<List<ToDoData>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend  fun insertData(toDoData: ToDoData)


    @Update
    suspend fun updateData(toDoData: ToDoData)

    @Delete
    suspend fun deleteItem(toDoData: ToDoData)


    @Query("DELETE  FROM todo_table")
    suspend fun deleteAll()



}