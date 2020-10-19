package com.app.taiye.todocleanarchitecture.ui.fragments.list

import androidx.recyclerview.widget.DiffUtil
import com.app.taiye.todocleanarchitecture.data.model.ToDoData

class TodoDiffUtil (
    private val oldList:List<ToDoData>,
    private val newList:List<ToDoData>): DiffUtil.Callback() {


    override fun getOldListSize(): Int = oldList.size


    override fun getNewListSize(): Int = newList.size


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id &&
               oldList[oldItemPosition].title == newList[newItemPosition].title &&
               oldList[oldItemPosition].description == newList[newItemPosition].description
    }


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }



}