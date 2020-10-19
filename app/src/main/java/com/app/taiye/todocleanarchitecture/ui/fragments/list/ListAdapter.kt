package com.app.taiye.todocleanarchitecture.ui.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.taiye.todocleanarchitecture.R
import com.app.taiye.todocleanarchitecture.data.Priority
import com.app.taiye.todocleanarchitecture.data.model.ToDoData
import com.app.taiye.todocleanarchitecture.databinding.RowLayoutBinding
import kotlinx.android.synthetic.main.row_layout.view.*

class ListAdapter:RecyclerView.Adapter<ListAdapter.ListViewHolder>(){

    var dataList = emptyList<ToDoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder.from(parent)
    }
    override fun getItemCount(): Int = dataList.size


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
      var currentItem = dataList[position]
      holder.bind(currentItem)
    }



    class ListViewHolder(private val binding:RowLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(todoData:ToDoData){
            binding.toDoData= todoData
            binding.executePendingBindings()
        }


        companion object{
            fun from(parent:ViewGroup):ListViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater,parent,false)
                return ListViewHolder(binding)
            }
        }
    }


    fun setData(toDoData: List<ToDoData>){
        this.dataList = toDoData
        notifyDataSetChanged()
    }

}