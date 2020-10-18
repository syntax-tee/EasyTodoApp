package com.app.taiye.todocleanarchitecture.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.app.taiye.todocleanarchitecture.data.TodoDatabase
import com.app.taiye.todocleanarchitecture.data.model.ToDoData
import com.app.taiye.todocleanarchitecture.data.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application):AndroidViewModel(application) {

    private  val todoDao = TodoDatabase.getDatabase(application).todoDao()

    private val repository:TodoRepository
    private val getAllData : LiveData<List<ToDoData>>

    init {
        repository = TodoRepository(todoDao)
        getAllData = repository.getAllData
    }

    fun insertData(toDoData: ToDoData){
        viewModelScope.launch (Dispatchers.IO){
            repository.insertData(toDoData)
        }
    }

}