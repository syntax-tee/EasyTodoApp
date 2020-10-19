package com.app.taiye.todocleanarchitecture.data.repository

import androidx.lifecycle.LiveData
import com.app.taiye.todocleanarchitecture.data.model.ToDoData
import com.app.taiye.todocleanarchitecture.data.dao.ToDoDao

class TodoRepository(val todoDao:ToDoDao) {

    val getAllData:LiveData<List<ToDoData>> = todoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData){
        todoDao.insertData(toDoData)
    }


    suspend fun updateData(toDoData: ToDoData){
        todoDao.updateData(toDoData)
    }

    suspend fun deleteAll(){
        todoDao.deleteAll()
    }

    suspend fun deleteItem(toDoData: ToDoData){
        todoDao.deleteItem(toDoData)
    }

}